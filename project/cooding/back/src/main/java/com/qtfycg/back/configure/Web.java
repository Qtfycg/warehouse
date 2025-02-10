package com.qtfycg.back.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Web implements WebMvcConfigurer {
    // Web配置类
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 允许所有域名访问
                .allowedMethods("**") // 允许所有方法
                .allowCredentials(true) // 允许携带cookie
                .maxAge(3600); // 1小时内不需要再预检
    }
}
