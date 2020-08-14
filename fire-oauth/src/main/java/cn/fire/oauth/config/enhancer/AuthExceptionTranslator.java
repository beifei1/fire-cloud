package cn.fire.oauth.config.enhancer;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.R;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;


/**
 * @Author: wangzc
 * @Date: 2020/8/13 16:48
 */

@Component
public class AuthExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.set("Pragma", "no-cache");

        if (e instanceof OAuth2Exception) {
            OAuth2Exception exception = (OAuth2Exception) e;
            return new ResponseEntity(new R(false, BaseException.BaseErrorEnum.OAUTH2_AUTH_DENY.getCode(), e.getMessage()), headers, HttpStatus.valueOf(exception.getHttpErrorCode()));
        }

        if (e instanceof AuthenticationException) {
            return new ResponseEntity(new R(false, BaseException.BaseErrorEnum.AUTHENCATION_DENY.getCode(), e.getMessage()), headers, HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new OAuth2Exception(e.getMessage()));
    }

}
