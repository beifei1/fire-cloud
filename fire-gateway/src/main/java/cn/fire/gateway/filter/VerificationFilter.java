package cn.fire.gateway.filter;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.response.R;
import cn.fire.gateway.util.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @Author: wangzc
 * @Date: 2020/8/21 11:12
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "gateway.verification.enable",havingValue = "true")
public class VerificationFilter implements GlobalFilter, Ordered {

    private static final String H_TIMESTAMP = "X-FIRE-TIMESTAMP";
    private static final String H_NONCE = "X-FIRE-NONCE";
    private static final String H_SIGN = "X-FIRE-SIGN";

    private RedisUtil redisUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String timestamp = exchange.getRequest().getHeaders().getFirst(H_TIMESTAMP);
        String nonce = exchange.getRequest().getHeaders().getFirst(H_NONCE);
        String sign = exchange.getRequest().getHeaders().getFirst(H_SIGN);

        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getHeaders().set("Cache-Control", "no-cache");

        if (StringUtils.isAnyBlank(timestamp,nonce,sign)) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            try {
                DataBuffer dataBuffer = response.bufferFactory().wrap(IOUtils.toByteArray(
                        JSONObject.toJSONString(R.fail(BaseException.BaseErrorEnum.REQUEST_SECURITY_VALID_ERROR.getCode(), BaseException.BaseErrorEnum.REQUEST_SECURITY_VALID_ERROR.getDescription()))
                ));
                return response.writeWith(Mono.just(dataBuffer));
            } catch (IOException e) {
                log.error("valid sign param error: {}", e);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
