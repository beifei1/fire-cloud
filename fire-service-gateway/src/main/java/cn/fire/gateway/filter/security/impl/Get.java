package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractProtect;
import cn.fire.gateway.filter.security.consts.Consts;
import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:17
 */

public class Get extends AbstractProtect implements Consts {

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.GET);
    }

    @Override
    protected Boolean verify() {
        return null;
    }
}
