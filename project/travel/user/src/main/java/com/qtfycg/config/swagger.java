package com.qtfycg.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swagger {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Travel项目接口文档")
                        .version("1.0")
                        .description("基于Spring Boot 3构建的旅游项目接口文档")
                        .contact(new Contact().name("qtfycg").email("1264318507@qq.com")));
    }
}
