package cn.fire.gateway.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/8/4 14:48
 */

@Configuration
public class SwaggerConfig {

    @Bean
    List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        //获取所有Route声明
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-consumer")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-consumer", "");
            swaggerUiConfigParameters.addGroup(name);
            GroupedOpenApi groupedOpenApi = GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
            groupedOpenApi.getOpenApiCustomisers().forEach(a -> {

            });
        });
        return groups;
    }

}
