package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractProtect;
import cn.fire.gateway.filter.security.consts.Consts;
import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Options全部为通过
 * @Author: wangzc
 * @Date: 2020/9/25 11:17
 */
@Component
public class Options extends AbstractProtect implements Consts {

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.OPTIONS);
    }

    @Override
    protected Boolean verify() {
        return Boolean.TRUE;
    }
}
