package com.qtfycg.config;

import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Configuration
public class JWT {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(String username) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("exp", System.currentTimeMillis() + expirationTime);

        return JWTUtil.createToken(payload, JWTSignerUtil.hs512(secret.getBytes()));
    }

    public String validateToken(String token) {
        cn.hutool.jwt.JWT jwt = JWTUtil.parseToken(token).setKey(secret.getBytes());
        if (jwt.validate(0)) {
            return (String) jwt.getPayload("username");
        } else {
            return null;
        }
    }
}
