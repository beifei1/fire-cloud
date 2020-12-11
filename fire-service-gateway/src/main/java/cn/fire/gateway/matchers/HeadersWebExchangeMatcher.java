package cn.fire.gateway.matchers;

import org.springframework.http.HttpHeaders;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: wangzc
 * @Date: 2020/12/11 9:36
 */

public class HeadersWebExchangeMatcher implements ServerWebExchangeMatcher {

    @Override
    public Mono<MatchResult> matches(ServerWebExchange serverWebExchange) {
        return null;
    }
}
