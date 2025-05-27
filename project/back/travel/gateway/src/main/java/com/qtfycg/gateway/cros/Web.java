package com.qtfycg.gateway.cros;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class Web implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 匹配所有路径
                .allowedOrigins("*") // 允许所有来源
                .allowedMethods("*") // 允许所有方法（GET, POST, PUT, DELETE 等）
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(true); // 允许发送凭据
    }
}
