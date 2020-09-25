package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractProtect;
import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:09
 */

public class Post extends AbstractProtect {

    public Post(ServerHttpRequest httpRequest, String timestamp, String nonce, String sign) {
        super(httpRequest, timestamp, nonce, sign);
    }

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.POST);
    }

    @Override
    protected Boolean verify() {
        return null;
    }
}
