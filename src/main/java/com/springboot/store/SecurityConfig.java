package com.springboot.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;

    public SecurityConfig(JWTAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

                // -------------------------
                //  PUBLIC ENDPOINTS
                // -------------------------
                .requestMatchers("/auth/**").permitAll()

                // -------------------------
                //  ADMIN ONLY
                // -------------------------
                .requestMatchers("/users/**").hasAuthority("ADMIN")

                // -------------------------
                //  PROJECT ENDPOINTS
                // -------------------------
                .requestMatchers(HttpMethod.GET, "/Project/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/Project/**").hasAnyAuthority("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.PUT, "/Project/**").hasAnyAuthority("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/Project/**").hasAnyAuthority("ADMIN", "MANAGER")

                // -------------------------
                //  SOFTWARE ENGINEER ENDPOINTS
                // -------------------------
                .requestMatchers(HttpMethod.GET, "/SoftwareEngineers/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/SoftwareEngineers/**").hasAnyAuthority("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.PUT, "/SoftwareEngineers/**").hasAnyAuthority("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/SoftwareEngineers/**").hasAnyAuthority("ADMIN", "MANAGER")

                // -------------------------
                //  ANY OTHER ENDPOINT
                // -------------------------
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}
