package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/9/25 11:17
 */
@Slf4j
@Component
public class Get extends AbstractMethod {

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.GET);
    }

    @Override
    protected Boolean verify() {
        log.info("GET 验证逻辑");
        return null;
    }
}
