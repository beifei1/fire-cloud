package cn.fire.gateway.filter;

import cn.fire.common.web.consts.WebConsts;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * @Author: wangzc
 * @Date: 2020/9/1 13:53
 */
@Slf4j
@Component
public class UserProfileFilter implements GlobalFilter, Ordered {

    private final String BEARER_PREFIEX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(token) || !StringUtils.startsWith(token, BEARER_PREFIEX)) {
            return chain.filter(exchange);
        }
        try {
            String realToken = token.replace(BEARER_PREFIEX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();

            ServerHttpRequest request = exchange.getRequest().mutate().header(WebConsts.USER_PROFILE_HEADER_NAME, userStr).build();
            exchange = exchange.mutate().request(request).build();

        } catch (ParseException e) {
            e.printStackTrace();
            log.error("解析Token时发生异常，详见:{}", e.getMessage());
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
