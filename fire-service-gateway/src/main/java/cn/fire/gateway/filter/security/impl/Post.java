package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractProtect;
import cn.fire.gateway.filter.security.consts.Consts;
import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:09
 */
@Component
public class Post extends AbstractProtect implements Consts {
    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.POST);
    }

    @Override
    protected Boolean verify() {
        return Boolean.TRUE;
    }
}
