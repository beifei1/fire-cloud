package cn.fire.gateway.config;

import cn.fire.gateway.authorization.CustomerAuthorizationManager;
import cn.fire.gateway.handler.OAuth2AuthExceptionEntryPoint;
import cn.fire.gateway.handler.Oauth2AccessDenyEntryPoint;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.util.FileCopyUtils;
import sun.security.rsa.RSAPublicKeyImpl;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author: wangzc
 * @Date: 2020/9/1 11:33
 */

@Slf4j
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    private final IgnoreConfigUrl configUrl;
    private final CustomerAuthorizationManager authorizationManager;
    private final Oauth2AccessDenyEntryPoint accessDenyEntryPoint;
    private final OAuth2AuthExceptionEntryPoint authExceptionEntryPoint;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http.oauth2ResourceServer()
                .jwt().publicKey(publicKey());
        http.authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(configUrl.getUrls().toArray(new String[configUrl.getUrls().size()])).permitAll()
                .pathMatchers("/oauth/**","/doc.html","/swagger-resources","/webjars/**","/**/v2/api-docs").permitAll()
                .anyExchange().access(authorizationManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDenyEntryPoint)
                .authenticationEntryPoint(authExceptionEntryPoint)
                .and()
                .csrf().disable();

        return http.build();
    }

    public RSAPublicKey publicKey() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("static/firecloud.jks"),"123456".toCharArray());
        return (RSAPublicKey) keyStoreKeyFactory.getKeyPair("firecloud").getPublic();
    }

}
