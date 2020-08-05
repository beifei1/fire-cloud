package cn.fire.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: wangzc
 * @Date: 2020/8/4 14:48
 */

@Configuration
public class SwaggerConfig {

    @Autowired
    private RouteDescription routeDescription;


}
