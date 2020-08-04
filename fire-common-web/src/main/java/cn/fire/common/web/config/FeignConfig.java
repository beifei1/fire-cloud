package cn.fire.common.web.config;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.Logger;
import feign.Response;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.Charset;

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

    @Bean
    Retryer retryer () { return Retryer.NEVER_RETRY; }

    @Bean
    ErrorDecoder errorDecoder() {
        return new BusinessErrorHandler();
    }


    BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(httpBasicUserName, httpBasicPassword);
    }

}

@Slf4j
class BusinessErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == HttpStatus.NOT_ACCEPTABLE.value()) {

            try {
                R r = JSONObject.parseObject(
                        IOUtils.toString(response.body().asReader(Charset.defaultCharset())),
                        R.class
                );
                return BaseException.instance(r.getMeta().getCode(), r.getMeta().getMsg());
            } catch (IOException e) {
                log.error("处理异常响应体错误:{}", e.getMessage());
            }

        }
        return BaseException.instance(response.status(),response.reason());
    }

}

