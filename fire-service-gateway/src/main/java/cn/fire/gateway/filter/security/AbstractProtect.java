package cn.fire.gateway.filter.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:04
 */

@Data
public abstract class AbstractProtect {

    private ServerHttpRequest httpRequest;
    private String timestamp;
    private String nonce;
    private String sign;

    public AbstractProtect() {}

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
