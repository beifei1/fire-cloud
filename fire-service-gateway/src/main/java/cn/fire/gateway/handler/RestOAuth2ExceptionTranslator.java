package cn.fire.gateway.handler;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.server.resource.web.server.BearerTokenServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @Author: wangzc
 * @Date: 2020/9/1 16:17
 */
//@Primary
//@Component
//public class RestOAuth2ExceptionTranslator extends BearerTokenServerAuthenticationEntryPoint {
//
//    @Override
//    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
//        return new ResponseEntity<OAuth2Exception>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
