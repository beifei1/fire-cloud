package cn.fire.oauth.config;

import cn.fire.oauth.config.enhancer.JwtTokenEnhancer;
import cn.fire.oauth.granter.impl.MobilePasswordTokenGranter;
import cn.fire.oauth.granter.impl.MobileSmsCodeTokenGranter;
import cn.fire.oauth.service.IUserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.*;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 9:30
 */

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("app").secret(passwordEncoder.encode("123456")).scopes("app").authorizedGrantTypes("refresh_token","password","mobile_password","mobile_smscode")
                .and()
                .withClient("system").secret(passwordEncoder.encode("123456")).scopes("system").authorizedGrantTypes("refresh_token","password","mobile_smscode","mobile_password")
                .accessTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        List<TokenGranter> tokenGranters = getTokenGranter(userService,endpoints);

        endpoints.tokenStore(tokenStore())
                .tokenGranter(new CompositeTokenGranter(tokenGranters))
                .tokenEnhancer(tokenEnhancerChain())
                .authenticationManager(authenticationManager);
    }

    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    private JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("static/cnsesan-jwt.jks"),"cnsesan123".toCharArray());

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("cnsesan-jwt"));

        return converter;
    }

    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        List<TokenEnhancer> enhancers = Lists.newArrayList();
        enhancers.add(jwtTokenEnhancer);
        enhancers.add(jwtAccessTokenConverter());

        tokenEnhancerChain.setTokenEnhancers(enhancers);
        return tokenEnhancerChain;
    }


    private  List<TokenGranter> getTokenGranter(IUserService userService,AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        List<TokenGranter> tokenGranters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));

        tokenGranters.add(new MobileSmsCodeTokenGranter(
                userService,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()
        ));

        tokenGranters.add(new MobilePasswordTokenGranter(
                userService,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()
        ));

        return tokenGranters;
    }

}
