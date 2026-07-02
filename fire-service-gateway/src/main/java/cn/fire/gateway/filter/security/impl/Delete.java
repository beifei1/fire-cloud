package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DELETE 请求防重放验证
 * 签名规则：MD5(timestamp + nonce + queryString + path)
 * @Author: wangzc
 * @Date: 2020/9/25 13:33
 */
@Slf4j
@Component
public class Delete extends AbstractMethod {

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.DELETE);
    }

    @Override
    protected Boolean verify() {
        // 1. 时间戳有效性校验（默认±5分钟窗口）
        if (!validateTimestamp()) {
            log.warn("DELETE request timestamp validation failed, path: {}", getHttpRequest().getURI().getPath());
            return Boolean.FALSE;
        }
        // 2. Nonce防重放校验（SETNX原子操作确保同一nonce只能使用一次）
        if (!validateNonce()) {
            log.warn("DELETE request nonce already used, path: {}", getHttpRequest().getURI().getPath());
            return Boolean.FALSE;
        }
        // 3. 签名校验（DELETE通常也带Query参数）
        String expectedSign = computeSignWithQuery();
        return validateSign(expectedSign);
    }
}
