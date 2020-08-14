package cn.fire.oauth.granter.impl;

import cn.fire.oauth.granter.AbstractCustomTokenGranter;
import cn.fire.oauth.pojo.dto.UserDTO;
import cn.fire.oauth.service.IUserService;
import lombok.Data;
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
 * @Date: 2020/8/13 15:19
 */

public class MobileSmsCodeTokenGranter extends AbstractCustomTokenGranter {

    private final IUserService userService;

    private final String _PARAM_MOBILE = "mobile";
    private final String _PARAM_SMSCODE = "smscode";

    public MobileSmsCodeTokenGranter(IUserService userService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "mobile_smscode");
        this.userService = userService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) throws AuthenticationException {
        Map<String,String> param = tokenRequest.getRequestParameters();

        if (!param.containsKey(_PARAM_MOBILE)) {
            throw new BadCredentialsException("missing param " + _PARAM_MOBILE);
        }
        if (!param.containsKey(_PARAM_SMSCODE)) {
            throw new BadCredentialsException("missing param " + _PARAM_SMSCODE);
        }
        UserDTO user = getUser(param);
        if(Objects.isNull(user)) {
            throw new UsernameNotFoundException("手机号或验证码错误");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(),"N/A",user.getAuthorities());

        return new OAuth2Authentication(tokenRequest.createOAuth2Request(client),authentication);
    }

    @Override
    protected UserDTO getUser(Map<String, String> param) {

        String mobile = param.get(_PARAM_MOBILE);
        String smsCode = param.get(_PARAM_SMSCODE);

        return userService.getByMobileAndCode(mobile,smsCode);
    }
}
