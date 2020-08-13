package cn.fire.oauth.controller;

import cn.fire.common.web.core.R;
import cn.fire.oauth.pojo.UserAuthVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Map;


/**
 * @Author: wangzc
 * @Date: 2020/8/7 10:57
 */

@Api(tags = "认证授权控制器")
@RestController
@RequestMapping("/oauth")
public class AuthorizationController {

    @Autowired
    private TokenEndpoint tokenEndpoint;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type",value = "授权类型",defaultValue = "password",required = true),
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    @ApiOperation("认证授权")
    @ApiOperationSupport(author = "beifei")
    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    public R<UserAuthVO> get(@ApiIgnore Principal principal,
                                     @RequestParam("grant_type") String grantType,
                                     @RequestParam("username") String username,
                                     @RequestParam("password") String password) throws HttpRequestMethodNotSupportedException {
        Map<String,String> param = Maps.newLinkedHashMapWithExpectedSize(3);
        param.put("grant_type", grantType);
        param.put("username", username);
        param.put("password", password);

        return R.ok(define(tokenEndpoint.postAccessToken(principal,param).getBody()));
    }


    protected UserAuthVO define(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken)accessToken;
        return UserAuthVO.builder()
                .accessToken(defaultOAuth2AccessToken.getValue())
                .expireSeconds(defaultOAuth2AccessToken.getExpiresIn())
                .refreshToken(defaultOAuth2AccessToken.getRefreshToken().getValue())
                .scope(defaultOAuth2AccessToken.getScope())
                .tokenType(defaultOAuth2AccessToken.getTokenType())
                .build();
    }



}
