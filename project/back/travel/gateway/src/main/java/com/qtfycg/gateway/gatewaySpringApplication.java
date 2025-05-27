package com.qtfycg.gateway;

import com.qtfycg.gateway.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class gatewaySpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(gatewaySpringApplication.class, args);
    }
}
