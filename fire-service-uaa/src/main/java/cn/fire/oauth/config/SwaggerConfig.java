package cn.fire.oauth.config;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.config.AbstractSwaggerConfig;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @Author: wangzc
 * @Date: 2020/8/5 13:37
 */
@EnableSwagger2
@EnableKnife4j
@Configuration
public class SwaggerConfig extends AbstractSwaggerConfig {


    @PostConstruct
    void init () {
        Arrays.stream(BaseException.BaseErrorEnum.values()).forEach(em -> {
            codes.add(new ResponseMessageBuilder().code(em.getCode()).message(em.getDescription()).build());
        });
    }

    public SwaggerConfig() {
        super("cn.fire.oauth.controller","授权服务文档","授权服务文档", codes);
    }
}
