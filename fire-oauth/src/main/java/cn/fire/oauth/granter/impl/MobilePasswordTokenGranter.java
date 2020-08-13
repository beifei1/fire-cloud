package cn.fire.oauth.granter.impl;

import cn.fire.oauth.granter.AbstractCustomTokenGranter;
import cn.fire.oauth.pojo.dto.UserDTO;
import cn.fire.oauth.service.IUserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: wangzc
 * @Date: 2020/8/13 15:12
 */

public class MobilePasswordTokenGranter extends AbstractCustomTokenGranter {

    private final IUserService userService;

    private final String _PARAM_MOBILE = "mobile";
    private final String _PARAM_PASSWORD = "smscode";

    public MobilePasswordTokenGranter(IUserService userService,AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "mobile_password");
        this.userService = userService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) throws AuthenticationException {
        Map<String,String> param = tokenRequest.getRequestParameters();

        if (!param.containsKey(_PARAM_MOBILE)) {
            throw new BadCredentialsException("missing param: " + _PARAM_MOBILE);
        }
        if (!param.containsKey(_PARAM_PASSWORD)) {
            throw new BadCredentialsException("missing param: " + _PARAM_PASSWORD);
        }
        UserDTO user = getUser(param);
        if(Objects.isNull(user)) {
            throw new UsernameNotFoundException("手机号或密码错误");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(),"N/A",user.getAuthorities());

        return new OAuth2Authentication(tokenRequest.createOAuth2Request(client),authentication);
    }

    @Override
    protected UserDTO getUser(Map<String, String> param) {
        String mobile = param.get(_PARAM_MOBILE);
        String password = param.get(_PARAM_PASSWORD);

        return userService.getByMobileAndPassword(mobile,password);
    }
}
