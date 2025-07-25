package com.qtfycg.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class notifySpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(notifySpringApplication.class, args);
    }
}
