package cn.fire.gateway.filter.security;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:04
 */

@Data
public abstract class AbstractMethod {

    private ServerHttpRequest httpRequest;
    private String timestamp;
    private String nonce;
    private String sign;

    /**
     * 放开构造方法
     */
    public AbstractMethod() {}

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

}
