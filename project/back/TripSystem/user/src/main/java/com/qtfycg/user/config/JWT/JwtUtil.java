package com.qtfycg.common.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.access-token-ttl-minutes}")
    private long accessTtlMin;
    @Value("${jwt.refresh-token-ttl-days}")
    private long refreshTtlDays;

    private SecretKey key() {
        // 支持纯文本或 Base64；若是 Base64，请用 Decoders.BASE64.decode(secret)
        byte[] bytes = secret.length() > 44 ? Decoders.BASE64.decode(secret) : secret.getBytes();
        return Keys.hmacShaKeyFor(bytes);
    }

    public String generateAccessToken(Long userId, String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .header().add("typ","JWT").and()
                .issuer(issuer)
                .subject(String.valueOf(userId))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(accessTtlMin * 60)))
                .claim("username", username)
                .claim("scope", "USER")  // 角色/权限
                .signWith(key())
                .compact();
    }

    public String generateRefreshToken(Long userId) {
        Instant now = Instant.now();
        return Jwts.builder()
                .issuer(issuer)
                .subject(String.valueOf(userId))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(refreshTtlDays * 24 * 3600)))
                .claim("type","refresh")
                .signWith(key())
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token);
    }

    public boolean isExpired(String token) {
        try {
            return parse(token).getPayload().getExpiration().before(new Date());
        } catch (Exception e) { return true; }
    }
}
