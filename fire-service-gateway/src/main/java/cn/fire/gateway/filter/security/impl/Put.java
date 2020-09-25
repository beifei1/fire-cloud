package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractProtect;
import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 13:34
 */
public class Put extends AbstractProtect {

    public Put(ServerHttpRequest httpRequest, String timestamp, String nonce, String sign) {
        super(httpRequest, timestamp, nonce, sign);
    }

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.PUT);
    }

    @Override
    protected Boolean verify() {
        return null;
    }
}
