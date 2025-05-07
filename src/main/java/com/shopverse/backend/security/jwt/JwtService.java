package com.shopverse.backend.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.shopverse.backend.security.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;


import java.security.Key;
import java.util.*;


@Service
public class JwtService {
	
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @SuppressWarnings("unchecked")
	public List<String> extractRoles(String token) {
        return extractAllClaims(token).get("roles", List.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles().stream().map(role -> role.getRole()).toList());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(key)
                .compact();
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) &&
               extractAllClaims(token).getExpiration().after(new Date());
    }

}
