package com.qtfycg.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qtfycg")
public class productSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(productSpringApplication.class, args);
    }
}
