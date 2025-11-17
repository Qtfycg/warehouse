package com.qtfycg.user.config.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
public class JwtUtil {

    private static final String SECRET = "qtfycg";
    private static final long EXPIRE_TIME = 5 * 60 * 60 * 1000;
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    /**
     * 生成 Token
     */
    public  String generateToken(String phone, String role) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + EXPIRE_TIME);

        return JWT.create()
                .withSubject(phone)
                .withClaim("phone", phone)
                .withClaim("role", role)
                .withIssuedAt(now)
                .withExpiresAt(expire)
                .sign(ALGORITHM);
    }

    /**
     * 验证 Token 并解析内容
     */
    public  DecodedJWT parseToken(String token) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(token);
    }
    public String getPhone(String token){
        DecodedJWT decodedJWT = parseToken(token);
        return decodedJWT.getClaim("phone").asString();
    }
    public String getRole(String token){
        DecodedJWT decodedJWT = parseToken(token);
        return decodedJWT.getClaim("role").asString();
    }
}
