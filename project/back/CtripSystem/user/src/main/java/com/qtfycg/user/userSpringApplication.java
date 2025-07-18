package com.qtfycg.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.qtfycg")
@EnableFeignClients(basePackages = "com.qtfycg.user.feign")
public class userSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(userSpringApplication.class,args);
    }
}
