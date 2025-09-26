package com.qtfycg.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.qtfycg")
@EnableFeignClients(basePackages = "com.qtfycg.order.feign")
public class orderSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(orderSpringApplication.class, args);
    }
}
