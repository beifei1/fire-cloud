package cn.fire.oauth.config;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.response.R;
import cn.fire.oauth.consts.GrantTypesEnum;
import cn.fire.oauth.granter.impl.MobileSmsCodeTokenGranter;
import cn.fire.oauth.pojo.dto.UserDTO;
import cn.fire.oauth.service.IUserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.*;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 9:30
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    /**
     * 配置客户端信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                    .withClient("app").secret(passwordEncoder.encode("123456")).scopes("app")
                    .authorizedGrantTypes(GrantTypesEnum.PASSWORD.getName(), GrantTypesEnum.REFRESH_TOKEN.getName(), GrantTypesEnum.SMS.getName())
                .and()
                    .withClient("system").secret(passwordEncoder.encode("123456")).scopes("system")
                    .authorizedGrantTypes(GrantTypesEnum.PASSWORD.getName(), GrantTypesEnum.REFRESH_TOKEN.getName(), GrantTypesEnum.SMS.getName())
                .accessTokenValiditySeconds(3600);
    }

    /**
     * 授权模式,授权管理器，自定义异常链路处理
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        List<TokenGranter> tokenGranters = getTokenGranter(userService,endpoints);

        endpoints.tokenStore(tokenStore())
                .tokenGranter(new CompositeTokenGranter(tokenGranters))
                .tokenEnhancer(tokenEnhancerChain())
                .exceptionTranslator(loggingExceptionTranslator())
                .authenticationManager(authenticationManager);
    }

    /**
     * JWTToken解析
     * @return
     */
    private JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    /**
     * 公私钥加解密
     * @return
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("static/firecloud.jks"),"123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("firecloud");
    }

    /**
     * 在Jwt中加入自定义信息
     * @return
     */
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        List<TokenEnhancer> enhancers = Lists.newArrayList();

        enhancers.add((token,authentication) -> {
            UserDTO user = (UserDTO) authentication.getUserAuthentication().getPrincipal();
            Map<String, Object> echance = Maps.newHashMap();
            echance.put("user_id", user.getId());
            echance.put("mobile", user.getMobile());
            echance.put("gender", user.getGender());
            ((DefaultOAuth2AccessToken) token).setAdditionalInformation(echance);
            return token;
        });

        enhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(enhancers);
        return tokenEnhancerChain;
    }

    /**
     * 先获取原有的授权模式，再加入新的授权模式
     * @param userService
     * @param endpoints
     * @return
     * @throws Exception
     */
    private  List<TokenGranter> getTokenGranter(IUserService userService,AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        List<TokenGranter> tokenGranters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));

        tokenGranters.add(new MobileSmsCodeTokenGranter(
                userService,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()
        ));

        return tokenGranters;
    }

    /**
     * OAuth2 Exception自定义处理
     * @return
     */
    @Bean
    public WebResponseExceptionTranslator loggingExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());

                return new ResponseEntity(new R(false, BaseException.BaseErrorEnum.OAUTH2_AUTH_DENY.getCode(), e.getMessage()), headers, responseEntity.getStatusCode());
            }
        };
    }

    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
