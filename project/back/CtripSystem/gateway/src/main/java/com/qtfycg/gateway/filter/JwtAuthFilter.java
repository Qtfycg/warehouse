package com.qtfycg.gateway.filter;

import com.qtfycg.common.JWT.jwtUtils;
import com.qtfycg.gateway.config.IgnoreUrlsConfig;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {
    @Resource
    jwtUtils jwtUtils;
    @Resource
    IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();
        String token = req.getHeaders().getFirst("Authorization");
        String path = exchange.getRequest().getURI().getPath();

        if (ignoreUrlsConfig.getUrls().stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        if (token == null || !token.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            jwtUtils.getUserId(token.replace("Bearer ", ""));
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100; // 优先执行
    }
}
