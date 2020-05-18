package com.zlrx.springreact.springreact.security;


import com.zlrx.springreact.springreact.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private Logger logger = LoggerFactory.getLogger(getClass());

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
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiryDate.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.debug("Invalid token signature {}", token);
        } catch (MalformedJwtException ex) {
            logger.debug("Invalid token {}", token);
        } catch (ExpiredJwtException ex) {
            logger.debug("Token expired {}", token);
        } catch (UnsupportedJwtException ex) {
            logger.debug("Unsupported token {}", token);
        } catch (IllegalArgumentException ex) {
            logger.debug("JWT claims is empty {}", token);
        }
        return false;
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        Long id = Long.parseLong(claims.get("id", String.class));
        return id;
    }


}
