package cn.fire.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: wangzc
 * @Date: 2020/8/5 10:04
 */

@Data
@Component
@ConfigurationProperties(prefix = "customer.route.define.map")
public class RouteDescription {

    private Map<String,String> routes = new LinkedHashMap<>();

}
