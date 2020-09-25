package cn.fire.gateway.filter.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:04
 */

@AllArgsConstructor
public abstract class AbstractProtect {

    private final ServerHttpRequest httpRequest;
    private final HttpMethod httpMethod;
    private final String timestamp;
    private final String nonce;
    private final String sign;

    protected void isSupport() throws HttpRequestMethodNotSupportedException {
        List<HttpMethod> methods = supportMethods();
        if (!methods.contains(httpMethod)) {
            throw new HttpRequestMethodNotSupportedException("不支持的请求方式");
        }
    }

    protected abstract List<HttpMethod> supportMethods();

    protected abstract Boolean verify();

    public Boolean isPassed() {
        try {
            isSupport();
        } catch (HttpRequestMethodNotSupportedException ex) {
            return Boolean.FALSE;
        }
        return verify();
    }

}
