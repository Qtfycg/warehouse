package com.qtfycg.user.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Web implements WebMvcConfigurer {
    /**
     * 配置跨域请求
     *
     * @param registry 跨域注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 匹配所有路径
                .allowedOrigins("**") // 允许的域名
                .allowedMethods("*") // 允许的请求方法
                .allowedHeaders("*") // 允许的请求头
                .maxAge(3600) // 预检请求的缓存时间
                .allowCredentials(false); // 是否允许发送 Cookie
    }
}
