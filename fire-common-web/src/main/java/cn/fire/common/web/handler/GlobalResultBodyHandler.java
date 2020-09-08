package cn.fire.common.web.handler;

import cn.fire.common.web.anno.EnhanceBody;
import cn.fire.common.web.core.response.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * @Author: wangzc
 * @Date: 2020/9/8 9:32
 */

@RestControllerAdvice
public class GlobalResultBodyHandler implements ResponseBodyAdvice<Object> {

    private final Class<? extends Annotation> ANNOTATION_TYPE = EnhanceBody.class;

    @Override
    public boolean supports(MethodParameter param, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.nonNull(param.getContainingClass().getAnnotation(ANNOTATION_TYPE)) || (param.hasMethodAnnotation(ANNOTATION_TYPE));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> converter, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof R) {
            return body;
        }

        if (Objects.isNull(body)) {
            return R.ok();
        }

        return R.ok(body);
    }
}
