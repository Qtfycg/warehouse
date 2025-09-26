package com.qtfycg.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = "com.qtfycg")
@EnableFeignClients(basePackages = "com.qtfycg.admin.feign")
public class adminSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(adminSpringApplication.class, args);
    }
}
