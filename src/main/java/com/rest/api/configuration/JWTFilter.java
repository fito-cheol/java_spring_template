package com.rest.api.configuration;

import com.rest.api.dto.UserDTO;
import com.rest.api.service.UserService;
import com.rest.api.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public JWTFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        authorizeRequest(request);
        filterChain.doFilter(request, response);
    }
    private void authorizeRequest(HttpServletRequest request){
        logger.debug("Processing authentication for '{}'", request.getRequestURL());

        final String requestHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
            logger.warn("Authorization failed. No JWT token found -- requestHeader:"+ requestHeader);
            return;
        }

        String userEmail;
        String authToken = requestHeader.split(" ")[1];


        try {
            userEmail = jwtUtil.getUserEmailFromToken(authToken);
        } catch (IllegalArgumentException e) {
            logger.error("Error during getting username from token", e);
            return;
        } catch (ExpiredJwtException e) {
            logger.warn("The token has expired", e);
            return;
        }

        if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) return;

        logger.debug("Security context was null, so authorizing user '{}'...", userEmail);

        UserDTO userDTO = this.userService.findUserByEmail(userEmail);

        if (!jwtUtil.validateToken(authToken, userDTO)) {
            logger.error("Not a valid token!!!");
            return;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userEmail, null, List.of(new SimpleGrantedAuthority("USER")));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        logger.info("Authorized user '{}', setting security context...", userEmail);
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

}
