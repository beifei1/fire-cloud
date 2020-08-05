package cn.fire.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "fire cloud microservice",
                version = "1.0",
                description = "fire cloud microservice demo api"
        )
)
public class FireGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireGatewayApplication.class, args);
    }

}
