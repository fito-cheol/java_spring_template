package com.rest.api.util;

import com.rest.api.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

// Ref: https://github.com/jwtk/jjwt#jwt-claims

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private final String jsonKey = "userEmail";

    public String create(UserDTO user){
        Claims claims = Jwts.claims();
        claims.put(jsonKey, user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserEmailFromToken(String token){
        Claims claims = parseToken(token);

        return claims.get(jsonKey, String.class);
    }
    public boolean checkTokenExpired(String token){
        return parseToken(token).getExpiration().before(new Date());
    }
    public Claims parseToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDTO userDTO) {

        final String userEmail = getUserEmailFromToken(token);
        final boolean isTokenExpired = checkTokenExpired(token);
        System.out.println("UserEmail:" + userEmail);
        System.out.println("Expired:" + isTokenExpired);

        return (
                userEmail.equals(userDTO.getEmail())
                        && !isTokenExpired
        );
    }
}
