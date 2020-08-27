package cn.fire.common.web.handler;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.response.R;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @Author: wangzc
 * @Date: 2020/8/6 13:32
 */
@Slf4j
public class ExceptionFeignDecoder extends SpringDecoder {

    public ExceptionFeignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
    }

    @Override
    public Object decode(Response response, Type type) throws FeignException, IOException,BaseException {

        byte[] bytes = IOUtils.toByteArray(response.body().asReader(Util.UTF_8));

        if (response.status() == HttpStatus.OK.value()) {
            R r = null;
            try {
                r = JSONObject.parseObject(IOUtils.toString(bytes,"UTF-8"), R.class);
            } catch (Exception ex) {}

            if (Objects.nonNull(r) && StringUtils.equals(r.getMeta().getSuccess(),String.valueOf(false))) {
                throw BaseException.instance(Integer.parseInt(r.getMeta().getCode()), r.getMeta().getMsg());
            }

        }

        Response newResponse = Response.builder()
                .body(bytes)
                .headers(response.headers())
                .reason(response.reason())
                .request(response.request())
                .status(response.status()).build();

        return super.decode(newResponse, type);
    }
}
