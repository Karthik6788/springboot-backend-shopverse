package com.shopverse.backend.security.controller;

import com.shopverse.backend.security.dto.AuthRequest;
import com.shopverse.backend.security.dto.AuthResponse;
import com.shopverse.backend.security.dto.RegisterRequest;
import com.shopverse.backend.security.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
