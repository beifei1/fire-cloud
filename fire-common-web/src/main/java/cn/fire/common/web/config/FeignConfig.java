package cn.fire.common.web.config;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.R;
import com.alibaba.fastjson.JSONObject;
import feign.Logger;
import feign.Response;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
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
public class FeignConfig {

    @Bean
    Retryer retryer () { return Retryer.NEVER_RETRY; }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}

@Slf4j
@Configuration
class BusinessErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == HttpStatus.OK.value()) {
            return null;
        }

        if (response.status() == HttpStatus.ACCEPTED.value()) {

            try {
                String body = IOUtils.toString(response.body().asReader(Charset.defaultCharset()));
                R r = JSONObject.parseObject(body, R.class);
                throw BaseException.instance(r.getMeta().getCode(), r.getMeta().getMsg());
            } catch (IOException e) {
                log.error("处理异常响应体错误:{}", e.getMessage());
            }

        }

        return null;
    }

}

