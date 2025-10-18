package com.online.bookshop.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // small demo secret — replace with secure random 256-bit in production (from properties)
    private final Key key = Keys.hmacShaKeyFor("replace_this_with_a_very_long_secret_key_which_is_at_least_32_bytes".getBytes());
    private final long expirationMillis = 1000L * 60 * 60 * 24; // 24h

    public String generateToken(String username, Long userId) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .claim("uid", userId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> validateToken(String token) throws JwtException {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
