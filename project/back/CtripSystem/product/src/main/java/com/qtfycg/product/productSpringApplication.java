package com.qtfycg.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qtfycg")
public class gatewaySpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(gatewaySpringApplication.class, args);
    }
}
