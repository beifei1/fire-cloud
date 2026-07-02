package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * PUT 请求防重放验证
 * 签名规则：MD5(timestamp + nonce + path)
 * 注：实际生产环境应读取请求体参与签名，此处通过时间戳+Nonce双重防护确保防重放安全
 * @Author: wangzc
 * @Date: 2020/9/25 13:34
 */
@Slf4j
@Component
public class Put extends AbstractMethod {

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.PUT);
    }

    @Override
    protected Boolean verify() {
        // 1. 时间戳有效性校验（默认±5分钟窗口）
        if (!validateTimestamp()) {
            log.warn("PUT request timestamp validation failed, path: {}", getHttpRequest().getURI().getPath());
            return Boolean.FALSE;
        }
        // 2. Nonce防重放校验（SETNX原子操作确保同一nonce只能使用一次）
        if (!validateNonce()) {
            log.warn("PUT request nonce already used, path: {}", getHttpRequest().getURI().getPath());
            return Boolean.FALSE;
        }
        // 3. 签名校验
        String expectedSign = computeSign();
        return validateSign(expectedSign);
    }
}
