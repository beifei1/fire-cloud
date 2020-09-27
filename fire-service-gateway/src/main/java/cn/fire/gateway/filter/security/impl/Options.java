package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Options全部为通过
 * @Author: wangzc
 * @Date: 2020/9/25 11:17
 */
@Slf4j
@Component
public class Options extends AbstractMethod {

    @Override
    protected List<HttpMethod> supportMethods() {
        return Lists.newArrayList(HttpMethod.OPTIONS);
    }

    @Override
    protected Boolean verify() {
        log.info("OPTIONS 验证逻辑");
        return Boolean.TRUE;
    }
}
