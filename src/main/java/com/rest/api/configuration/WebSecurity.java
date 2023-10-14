package com.rest.api.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

// https://www.toptal.com/spring/spring-security-tutorial
// Enable CORS and disable CSRF.
// Set session management to stateless.
// Set unauthorized requests exception handler.
// Set permissions on endpoints.
// Add JWT token filter.

@Configuration
@EnableWebSecurity
public class WebSecurity {
    public static final String[] PUBLIC_PATHS = {"/api/auth/**",
            "/v3/api-docs.yaml",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Enable CORS and disable CSRF.
        http.httpBasic((basic)-> basic.disable())
                .cors(Customizer.withDefaults())
                .csrf((csrf) -> csrf.disable());
        // Set session management to stateless.
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // Set unauthorized requests exception handler.
        http.exceptionHandling((exceptionHandling) ->{
            AuthenticationEntryPoint authenticationEntryPoint = ((request, response, authException) -> {response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());});
            RequestMatcher requestMatcher = request -> {
                return false;
            };


            ExceptionHandlingConfigurer<HttpSecurity> httpSecurityExceptionHandlingConfigurer = exceptionHandling.defaultAuthenticationEntryPointFor(authenticationEntryPoint, requestMatcher);
        });
        // Set permissions on endpoints.
        http.authorizeHttpRequests(
                (authorizeRequests) ->
                        authorizeRequests.requestMatchers(HttpMethod.POST, "/user/register").authenticated()
                                .requestMatchers("/user/login").permitAll()
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated()
        );
        // Add JWT token filter.
//        http.addFilterBefore(
//                jwtTokenFilter,
//                UsernamePasswordAuthenticationFilter.class
//        );

        return http.build();
    }
}
