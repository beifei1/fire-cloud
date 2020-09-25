package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractProtect;
import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Options全部为通过
 * @Author: wangzc
 * @Date: 2020/9/25 11:17
 */
@Component
public class OPTIONS extends AbstractProtect {

    public OPTIONS(HttpRequest httpRequest, HttpMethod httpMethod, String timestamp, String nonce, String sign) {
        super(httpRequest, httpMethod, timestamp, nonce, sign);
    }

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.OPTIONS);
    }

    @Override
    protected Boolean verify() {
        return Boolean.TRUE;
    }
}
