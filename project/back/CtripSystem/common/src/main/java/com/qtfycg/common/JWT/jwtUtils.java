package com.qtfycg.common.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Configuration
public class jwtUtils {
    private static final String SECRET = "qtfycg";
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000L; // 7天
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    /**
     * 生成 Token
     */
    public static String generateToken(Long userId, String role) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + EXPIRE_TIME);

        return JWT.create()
                .withSubject(userId.toString())
                .withClaim("role", role)
                .withIssuedAt(now)
                .withExpiresAt(expire)
                .sign(ALGORITHM);
    }

    /**
     * 验证 Token 并解析内容
     */
    public static DecodedJWT parseToken(String token) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(token);
    }

    public static Long getUserId(String token) {
        return Long.parseLong(parseToken(token).getSubject());
    }

    public static String getUserRole(String token) {
        return parseToken(token).getClaim("role").asString();
    }

}
