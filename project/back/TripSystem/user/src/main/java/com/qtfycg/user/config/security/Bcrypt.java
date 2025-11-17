package com.qtfycg.user.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.SecureRandom;

@Configuration
public class Bcrypt {
    @Bean
    public PasswordEncoder passwordEncoder() {
        int cost = 12; // 10–14
        return new BCryptPasswordEncoder(cost, new SecureRandom());
    }
}
