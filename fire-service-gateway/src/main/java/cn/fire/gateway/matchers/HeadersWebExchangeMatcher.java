package cn.fire.gateway.matchers;


import org.springframework.http.HttpHeaders;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: wangzc
 * @Date: 2020/12/11 9:36
 */

public class HeadersWebExchangeMatcher implements ServerWebExchangeMatcher {

    private MultiValueMap<String,String> multiValueMap;

    public HeadersWebExchangeMatcher(MultiValueMap<String,String> map) {
        this.multiValueMap = map;
    }

    public HeadersWebExchangeMatcher(String k, String v) {
//        MultiValueMap<String,String> map = Mul
    }

    @Override
    public Mono<MatchResult> matches(ServerWebExchange ex) {

//        ex.getRequest().getHeaders().containsKey()
        return null;
    }

}
