package cn.fire.common.web.config;

import cn.fire.common.web.handler.ExceptionFeignDecoder;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 11:30
 */

@Configuration
@ConditionalOnClass(feign.Retryer.class)
public class FeignConfig {

    @Value("${spring.security.user.name:}")
    private String httpBasicUserName;

    @Value("${spring.security.user.password:}")
    private String httpBasicPassword;

    @Autowired
    private ObjectFactory<HttpMessageConverters> httpMessageConverterObjectFactory;

    @Bean
    public Feign.Builder feignBuilder() { return Feign.builder(); }

    @Bean
    Retryer retryer () { return Retryer.NEVER_RETRY; }


    @Bean
    Decoder decoder() {
        return new ExceptionFeignDecoder(httpMessageConverterObjectFactory);
    }

    @Bean
    BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(httpBasicUserName, httpBasicPassword);
    }

}

