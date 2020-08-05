package cn.fire.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangzc
 * @Date: 2020/8/5 10:31
 */

@Configuration
@OpenAPIDefinition(info = @Info(title = "用户服务", version = "1.0", description = "用户服务"), servers = @Server(description = "用户服务"))
public class OperApiConfig {
}
