package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractProtect;
import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:09
 */

@Component
public class POST extends AbstractProtect {


    public POST(HttpRequest httpRequest, HttpMethod httpMethod, String timestamp, String nonce, String sign) {
        super(httpRequest, httpMethod, timestamp, nonce, sign);
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
