package cn.fire.oauth.config.enhancer;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.R;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 10:24
 */

@Slf4j
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        Throwable cause = e.getCause();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            if(cause instanceof InvalidTokenException) {
                response.getWriter().write(JSONObject.toJSONString(R.fail(BaseException.BaseErrorEnum.INVALID_TOKEN.getCode(),"无效的Token")));
            }else{
                response.getWriter().write(JSONObject.toJSONString(R.fail(e.getMessage())));
            }
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

}
