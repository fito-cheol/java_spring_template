package com.rest.api.util;

import com.rest.api.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

// Ref: https://github.com/jwtk/jjwt#jwt-claims
public class Jwt {
    public static String create(UserDTO user, String secretKey, Long expireMS){
        Claims claims = Jwts.claims();
        claims.put("userEmail", user.getEmail());

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ expireMS))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
