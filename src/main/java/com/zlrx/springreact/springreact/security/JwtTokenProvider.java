package com.zlrx.springreact.springreact.security;


import com.zlrx.springreact.springreact.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusSeconds(SecurityConstants.EXPIRATION_TIME_SECONDS);
        String userId = Long.toString(user.getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getUsername());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
                .setExpiration(Date.from(expiryDate.toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();

    }


}
