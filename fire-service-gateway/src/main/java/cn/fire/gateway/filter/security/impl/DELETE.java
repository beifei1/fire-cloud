package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractProtect;
import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 13:33
 */
@Component
public class DELETE extends AbstractProtect {

    public DELETE(ServerHttpRequest httpRequest, HttpMethod httpMethod, String timestamp, String nonce, String sign) {
        super(httpRequest, httpMethod, timestamp, nonce, sign);
    }

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.DELETE);
    }

    @Override
    protected Boolean verify() {
        return null;
    }
}
