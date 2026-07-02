package cn.fire.gateway.filter.security;

import cn.fire.gateway.util.RedisUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:04
 */

@Slf4j
@Data
public abstract class AbstractMethod {

    /**
     * 默认时间戳有效窗口（毫秒），5分钟
     */
    private static final long DEFAULT_TIMESTAMP_WINDOW = TimeUnit.MINUTES.toMillis(5);

    /**
     * 默认nonce过期时间（秒），6分钟，略大于时间戳窗口
     */
    private static final long DEFAULT_NONCE_EXPIRE = TimeUnit.MINUTES.toSeconds(6);

    /**
     * Redis中nonce的key前缀
     */
    private static final String NONCE_PREFIX = "security:nonce:";

    private ServerHttpRequest httpRequest;
    private String timestamp;
    private String nonce;
    private String sign;

    private RedisUtil redisUtil;

    /**
     * 放开构造方法
     */
    public AbstractMethod() {}

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 子类支持的方法
     * @return 支持的Http方法集合
     */
    protected abstract List<HttpMethod> supportMethods();

    /**
     * 是否通过验证的逻辑
     * @return
     */
    protected abstract Boolean verify();

    /**
     * 是否通过验证
     * @return
     */
    public Boolean isPassed() {
        return isSupportMethod() && verify();
    }

    /**
     * 该方法验证器是否支持请求的Http方法
     * @return
     */
    protected boolean isSupportMethod() {
        List<HttpMethod> methods = supportMethods();
        return methods.contains(httpRequest.getMethod());
    }

    // ==================== 通用验证方法 ====================

    /**
     * 校验时间戳是否在有效窗口内
     * @return true-有效, false-过期
     */
    protected boolean validateTimestamp() {
        try {
            long requestTime = Long.parseLong(timestamp);
            long currentTime = System.currentTimeMillis();
            long diff = Math.abs(currentTime - requestTime);
            if (diff > DEFAULT_TIMESTAMP_WINDOW) {
                log.warn("Timestamp expired, diff: {}ms, requestTime: {}, currentTime: {}", diff, requestTime, currentTime);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            log.warn("Invalid timestamp format: {}", timestamp);
            return false;
        }
    }

    /**
     * 校验nonce是否已被使用（防重放）
     * 使用Redis SET NX原子操作，保证同一个nonce在有效期内只能被使用一次
     * @return true-首次使用, false-已被使用
     */
    protected boolean validateNonce() {
        if (redisUtil == null) {
            log.warn("RedisUtil is null, skip nonce validation");
            return true;
        }
        String key = NONCE_PREFIX + nonce;
        // SETNX 原子操作: 如果key不存在则设置成功返回true，已存在返回false
        boolean success = redisUtil.setIfAbsent(key, "1", DEFAULT_NONCE_EXPIRE);
        if (!success) {
            log.warn("Nonce already used: {}", nonce);
        }
        return success;
    }

    /**
     * 计算请求签名
     * 默认使用: MD5(timestamp + nonce + requestUri)
     * 子类可覆写以实现更复杂的签名算法
     * @return 计算出的签名字符串（HEX格式）
     */
    protected String computeSign() {
        String data = timestamp + nonce + httpRequest.getURI().getPath();
        return DigestUtils.md5Hex(data);
    }

    /**
     * 计算带Query参数的签名（用于GET/DELETE）
     * @return 计算出的签名字符串（HEX格式）
     */
    protected String computeSignWithQuery() {
        String query = httpRequest.getURI().getRawQuery();
        String queryPart = (query != null && !query.isEmpty()) ? query : "";
        String data = timestamp + nonce + queryPart + httpRequest.getURI().getPath();
        return DigestUtils.md5Hex(data);
    }

    /**
     * 验证签名是否匹配
     * @param expectedSign 期望的签名
     * @return true-匹配, false-不匹配
     */
    protected boolean validateSign(String expectedSign) {
        if (expectedSign == null || sign == null) {
            return false;
        }
        boolean valid = expectedSign.equalsIgnoreCase(sign);
        if (!valid) {
            log.warn("Sign mismatch, expected: {}, actual: {}", expectedSign, sign);
        }
        return valid;
    }

    /**
     * 对参数值进行URL编码
     */
    protected String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            return value;
        }
    }

}
