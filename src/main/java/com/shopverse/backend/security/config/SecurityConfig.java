package com.shopverse.backend.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shopverse.backend.security.jwt.JwtAthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private final JwtAthenticationFilter jwtAthenticationFilter;
	
	@Bean
	public SecurityFilterChain	filterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/**").permitAll()
                        //.requestMatchers("/admin/**").hasRole("ADMIN")
                        //.anyRequest().authenticated()
                		.anyRequest().permitAll()
                )      
                //.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.addFilterBefore(jwtAthenticationFilter, UsernamePasswordAuthenticationFilter.class)//athentication token
                .build();
	}
	
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
