package com.shopverse.backend.security.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopverse.backend.security.dto.AuthRequest;
import com.shopverse.backend.security.dto.AuthResponse;
import com.shopverse.backend.security.dto.RegisterRequest;
import com.shopverse.backend.security.jwt.JwtService;
import com.shopverse.backend.security.model.Role;
import com.shopverse.backend.security.model.User;
import com.shopverse.backend.security.repository.RoleRepository;
import com.shopverse.backend.security.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	
    public AuthResponse register(RegisterRequest request) {

    	Set<Role> roles=roleRepository.findById(1).stream().collect(Collectors.toSet());//USER
    	

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse login(AuthRequest request) {
    	
    	
        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //String encodedPassword=passwordEncoder.encode(request.getPassword());
        
        //System.out.print("Encoded Password->"+encodedPassword+" DB Password->"+user.getPassword()+" T/F->"+encodedPassword.equals(user.getPassword()));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

}
