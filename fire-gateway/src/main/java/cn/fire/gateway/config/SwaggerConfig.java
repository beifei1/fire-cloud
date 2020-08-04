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
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-consumer")).forEach(routeDefinition -> {
			String name = routeDefinition.getId().replaceAll("-consumer", "").replace("fire-","");
			swaggerUiConfigParameters.addGroup(name);
			swaggerUiConfigParameters.setUiRootPath(name);

            System.out.println(name);
//			GroupedOpenApi g = GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
		});
        return groups;
    }

}
