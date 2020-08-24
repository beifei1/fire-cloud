package cn.fire.common.web.handler;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.response.R;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wangzc
 * @Date: 2020/7/30 17:17
 */

@Slf4j
@Component("accessDenyEntryPoint")
@ConditionalOnClass(EnableResourceServer.class)
public class Oauth2AccessDenyEntryPoint implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(JSONObject.toJSONString(R.fail(BaseException.BaseErrorEnum.TOKEN_UNAUTHORIZAD.getCode(),"permission denied")));
        } catch (IOException ex) {
            log.error("接口权限不足");
        }
    }
}
