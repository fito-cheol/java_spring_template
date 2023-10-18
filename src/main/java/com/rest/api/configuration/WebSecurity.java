package com.rest.api.configuration;

import com.rest.api.service.UserService;
import com.rest.api.util.JwtUtil;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// https://www.toptal.com/spring/spring-security-tutorial
// Enable CORS and disable CSRF.
// Set session management to stateless. Ref: https://russwest.tistory.com/40
// Set unauthorized requests exception handler. <- 구현 안함 아래에서 포함되는듯
// Set permissions on endpoints.
// Add JWT token filter.

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final JwtUtil jwtUtil;

    private final UserService userService;
    // https://www.youtube.com/watch?v=2o_3hjUPAfQ
    // springdoc.org/v2/
    public static final String[] PUBLIC_PATHS = {"/error", "/user/login",
            "/swagger-ui.html", "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**",
            "/configuration/ui", "/configuration/security", "/swagger-ui/**", "/webjars/**"};

    @Autowired
    public WebSecurity(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Enable CORS and disable CSRF.
        http.httpBasic(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        // Set session management to stateless.
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Set permissions on endpoints.
        http.authorizeHttpRequests(
                (authorizeRequests) ->
                        authorizeRequests.requestMatchers(PUBLIC_PATHS).permitAll() // https://colabear754.tistory.com/182
                                .anyRequest().authenticated()
        );
        // Add JWT token filter.
        Filter authenticationTokenFilter = new JWTFilter(jwtUtil, userService);
        http.addFilterBefore(
                authenticationTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}
