package cn.fire.gateway.config;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RouteDescription routeDescription;

    @Bean
    List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();

        routeDescription.getRoutes().forEach((routeId,description) -> {
            definitions.stream().filter(routeDefinition -> routeDefinition.getId().equalsIgnoreCase(routeId)).forEach(routeDefinition -> {
                String name = StringUtils.replaceEachRepeatedly(routeDefinition.getId(), new String[]{"-","consumer","fire"},new String[]{"","",""});
                swaggerUiConfigParameters.addGroup(name);
                GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(description).build();
            });
        });

        return groups;
    }

}
