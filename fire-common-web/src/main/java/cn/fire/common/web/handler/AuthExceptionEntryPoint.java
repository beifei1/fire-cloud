package cn.fire.common.web.handler;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.R;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wangzc
 * @Date: 2020/7/30 17:12
 */

@Slf4j
@Component
@ConditionalOnClass(EnableResourceServer.class)
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Throwable cause = e.getCause();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        try {
            if(cause instanceof InvalidTokenException) {
                response.getWriter().write(JSONObject.toJSONString(R.fail(BaseException.INVALID_TOKEN,"无效的Token")));
            }else{
                response.getWriter().write(JSONObject.toJSONString(R.fail(e.getMessage())));
            }
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
