package cn.fire.gateway.filter.security.impl;

import cn.fire.gateway.filter.security.AbstractMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * OPTIONS 预检请求不做安全校验，直接放行
 * 浏览器CORS预检请求不会携带自定义Header，因此跳过所有验证
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
        // OPTIONS 预检请求不做任何安全校验，直接放行
        log.debug("OPTIONS preflight request, skip security validation");
        return Boolean.TRUE;
    }
}
