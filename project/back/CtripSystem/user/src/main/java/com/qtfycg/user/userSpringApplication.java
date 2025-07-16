package com.qtfycg.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.qtfycg.file.feign")
@SpringBootApplication(scanBasePackages = "com.qtfycg")
public class userSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(userSpringApplication.class, args);
    }
}
