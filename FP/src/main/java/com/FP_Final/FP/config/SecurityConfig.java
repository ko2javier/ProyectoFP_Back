package com.FP_Final.FP.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.security.Keys;

import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

	 @Value("${jwt.secret}") // Carga la clave secreta desde application.properties
	    private String secretKey;
	 
	 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
	        .cors(cors -> cors.configurationSource(request -> {
	            var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
	            corsConfiguration.addAllowedOrigin("http://localhost:4200");
	            corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	            corsConfiguration.addAllowedHeader("*");
	            return corsConfiguration;
	        }))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("auth/login", "/ventas/*").permitAll();
                auth.anyRequest().authenticated();
            })
            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, role FROM authorities WHERE username = ?");
        return manager;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Convierte la clave secreta en una clave adecuada para HMAC SHA-256
        return NimbusJwtDecoder.withSecretKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build();
    }
}
