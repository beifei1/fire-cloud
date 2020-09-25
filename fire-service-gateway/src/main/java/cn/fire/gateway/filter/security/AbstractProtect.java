package cn.fire.gateway.filter.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:04
 */

@AllArgsConstructor
public abstract class AbstractProtect {

    private final ServerHttpRequest httpRequest;
    private final String timestamp;
    private final String nonce;
    private final String sign;

    protected abstract List<HttpMethod> supportMethods();

    protected abstract Boolean verify();

    protected boolean isSupportMethod() {
        List<HttpMethod> methods = supportMethods();
        return methods.contains(httpRequest.getMethod());
    }

    public Boolean isPassed() {
        return isSupportMethod() && verify();
    }

}
