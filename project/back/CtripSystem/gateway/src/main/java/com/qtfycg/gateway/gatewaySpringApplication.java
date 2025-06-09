package com.qtfycg.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.qtfycg",exclude = { DataSourceAutoConfiguration.class })
public class gatewaySpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(gatewaySpringApplication.class, args);
    }
}
