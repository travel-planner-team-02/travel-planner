package com.laioffer.travelplannerbe.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtHandler {

    final Key signingKey;

    // JWT token generates need this, which is the 3rd part
    public JwtHandler(@Value("${travelplanner.jwt.secret-key}") String secretKey) {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        signingKey = Keys.hmacShaKeyFor(bytes);
    }

    // login dependency function, my logic is login automatically after register,
    // so it's also a dependency function of register
    public String parsedUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
