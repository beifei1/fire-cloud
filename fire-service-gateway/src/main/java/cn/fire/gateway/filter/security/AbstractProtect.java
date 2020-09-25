package cn.fire.gateway.filter.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.annotation.PostConstruct;
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


    @PostConstruct
    private void isSupport() throws HttpRequestMethodNotSupportedException {
        List<HttpMethod> methods = supportMethods();
        if (!methods.contains(httpMethod)) {
            throw new HttpRequestMethodNotSupportedException("不支持的请求方式");
        }
    }

    /**
     * 支持的方法类型
     *
     * @return
     */
    protected abstract List<HttpMethod> supportMethods();

    /**
     * 是否通过验证
     *
     * @return
     */
    public Boolean isPassed() {
        return verify();
    }

    /**
     * 验证逻辑
     *
     * @return
     */
    protected abstract Boolean verify();

}
