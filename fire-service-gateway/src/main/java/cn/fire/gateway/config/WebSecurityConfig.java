package cn.fire.gateway.config;

import cn.fire.gateway.handler.RestAuthExceptionEntryPoint;
import cn.fire.gateway.handler.RestAccessDenyEntryPoint;
import cn.fire.gateway.util.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.security.interfaces.RSAPublicKey;

/**
 * @Author: wangzc
 * @Date: 2020/9/1 11:33
 */

@Slf4j
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    private final IgnoreUriConfig configUrl;
    private final ReactiveAuthorizationManager authorizationManager;
    private final RestAccessDenyEntryPoint accessDenyEntryPoint;
    private final RestAuthExceptionEntryPoint authExceptionEntryPoint;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http.cors();
        http.oauth2ResourceServer().jwt()
                .publicKey(publicKey())
                .jwtAuthenticationConverter(jwtAuthenticationConverter());

        http.oauth2ResourceServer().authenticationEntryPoint(authExceptionEntryPoint);
        http.oauth2ResourceServer().accessDeniedHandler(accessDenyEntryPoint);

        http.authorizeExchange()

                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(configUrl.getUrls().toArray(new String[configUrl.getUrls().size()])).permitAll()
                .pathMatchers("/oauth/**","/doc.html","/swagger-resources","/webjars/**","/**/v2/api-docs","/actuator/**").permitAll()
                .pathMatchers("/**").authenticated()
                .anyExchange().access(authorizationManager)

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDenyEntryPoint)
                .authenticationEntryPoint(authExceptionEntryPoint)
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantConvert = new JwtGrantedAuthoritiesConverter();

        grantConvert.setAuthorityPrefix(Utils.Authority.ROLE_PREFIEX);
        grantConvert.setAuthoritiesClaimName(Utils.Authority.AUTHORITY_NAME);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantConvert);

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    @Bean
    public RSAPublicKey publicKey() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("static/firecloud.jks"),"123456".toCharArray());
        return (RSAPublicKey) keyStoreKeyFactory.getKeyPair("firecloud").getPublic();
    }


}
