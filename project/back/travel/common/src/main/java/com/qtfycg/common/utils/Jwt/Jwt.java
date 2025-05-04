package com.qtfycg.common.utils.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;

public class Jwt {
    // JWT的密钥，用于签名和验证Token
    private static final String secret_key = "qtfycg";
    // JWT的过期时间，单位为毫秒，这里设置为1小时（3600秒）
    private static final Long expire_time = 3600L * 1000;

    public static String getToken(String tel) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(expire_time);

        return JWT.create()
                .withSubject(String.valueOf(tel))  // 标准字段 sub
                .withIssuedAt(now)
                .withExpiresAt(expiry)
                .sign(Algorithm.HMAC256(secret_key));
    }

    /**
     * 校验并解析 token
     */
    public static DecodedJWT parseToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret_key)).build();
        return verifier.verify(token);
    }

    public static Integer getUserId(String token) {
        return Integer.valueOf(parseToken(token).getSubject());
    }

    public static boolean isExpired(String token) {
        return parseToken(token).getExpiresAtAsInstant().isBefore(Instant.now());
    }

}
