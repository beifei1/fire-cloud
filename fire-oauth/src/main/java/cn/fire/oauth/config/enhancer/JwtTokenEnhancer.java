package cn.fire.oauth.config.enhancer;

import cn.fire.oauth.pojo.dto.UserDTO;
import com.google.common.collect.Maps;
import org.springframework.security.core.userdetails.User;
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

        UserDTO user = (UserDTO)oAuth2Authentication.getUserAuthentication().getPrincipal();
        Map<String,Object> echance = Maps.newHashMap();
        echance.put("userName", user.getUsername());
        echance.put("userId", user.getId());
        echance.put("mobile", user.getMobile());
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(echance);

        return oAuth2AccessToken;
    }
}
