package com.qtfycg.gateway.filter;

import com.qtfycg.gateway.config.IgnoreUrlsConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {
    @Resource
    jwtUtils jwtUtils;
    @Resource
    IgnoreUrlsConfig ignoreUrlsConfig;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        /*
        * 获取请求信息
        * */
        ServerHttpRequest req = exchange.getRequest();
        String token = req.getHeaders().getFirst("Authorization");
        String path = exchange.getRequest().getURI().getPath();

        /*
        * 忽略特定路径
        * */
        if (ignoreUrlsConfig.getUrls().stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        /*
        * Token 验证
        * */
        if (token == null || !token.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        /*
        * 解析 Token
        * */
        try {
            String rawToken = token.replace("Bearer ", "");
            Long userId = jwtUtils.getUserId(rawToken);

            ServerHttpRequest mutatedRequest = req.mutate()
                    .header("X-User-Id", String.valueOf(userId))
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        } catch (Exception e) {
            log.warn("JWT 解析失败: {}", e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

    }

    @Override
    public int getOrder() {
        return -100; // 优先执行
    }
}
