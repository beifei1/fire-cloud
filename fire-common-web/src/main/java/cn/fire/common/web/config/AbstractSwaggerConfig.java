package cn.fire.common.web.config;

import cn.fire.common.exception.BaseException;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/8/27 13:56
 */

public abstract class AbstractSwaggerConfig {

    protected static List<ResponseMessage> codes = new ArrayList<>();

    private String scanBasePackage;
    private String docTitle;
    private String docDesc;

    static {
        Arrays.stream(BaseException.BaseErrorEnum.values()).forEach(em -> {
            codes.add(new ResponseMessageBuilder().code(em.getCode()).message(em.getDescription()).build());
        });
    }

    public AbstractSwaggerConfig(String scanBasePackage, String docTitle, String docDesc, List<ResponseMessage> responseMessages) {
        this.scanBasePackage = scanBasePackage;
        this.docDesc = docDesc;
        this.docTitle = docTitle;
        codes.addAll(responseMessages);
    }


    @Bean
    public Docket defaultApi() {
        List<Parameter> paramsList = new ArrayList<>();
        ParameterBuilder tokenParam = new ParameterBuilder()
                .name("Authorization")
                .description("access token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .hidden(false).required(false)
                .defaultValue("Bearer token");
        paramsList.add(tokenParam.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                .title(docTitle)
                .description(docDesc)
                .termsOfServiceUrl("https://github.com/beifei1/fire-cloud")
                .contact(new Contact("beifei", "https://github.com/beifei1/fire-cloud", "xxxxxxxxxx@163.com"))
                .version("1.0")
                .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(scanBasePackage))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalOperationParameters(paramsList)
                .globalResponseMessage(RequestMethod.POST,codes)
                .globalResponseMessage(RequestMethod.PUT,codes)
                .globalResponseMessage(RequestMethod.DELETE,codes)
                .globalResponseMessage(RequestMethod.GET,codes)
                .globalResponseMessage(RequestMethod.PATCH,codes);
    }


}
