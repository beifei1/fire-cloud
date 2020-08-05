package cn.fire.user.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/8/5 13:37
 */
@EnableSwagger2
@EnableKnife4j
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket defaultApi() {
        List<Parameter> paramsList = new ArrayList<>();
        ParameterBuilder tokenParam = new ParameterBuilder()
                .name("Authorization")
                .description("Oauth2 AccessToken")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .hidden(false).required(false)
                .defaultValue("Bearer empty");
        paramsList.add(tokenParam.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.fire.user.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(paramsList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户服务API文档")
                .description("用户服务接口文档")
                .termsOfServiceUrl("https://github.com/beifei1/fire-cloud")
                .contact(new Contact("beifei", "https://github.com/beifei1/fire-cloud", "xxxxxxxxxx@163.com"))
                .version("1.0")
                .build();
    }

}
