package cn.fire.oauth.config.enhancer;

import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 9:44
 */

@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Map<String,Object> echance = Maps.newHashMap();
        echance.put("extension","jwt tuozhanxinxi");

        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(echance);

        return oAuth2AccessToken;
    }
}
