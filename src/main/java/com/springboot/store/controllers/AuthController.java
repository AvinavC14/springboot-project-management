package com.springboot.store.controllers;

import com.springboot.store.dto.LoginRequest;
import com.springboot.store.dto.RegisterRequest;
import com.springboot.store.services.JwtService;
import com.springboot.store.services.UserService;
import com.springboot.store.dto.AuthResponse;
import com.springboot.store.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")  // Base path for authentication endpoints
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService , JwtService jwtService) {
        this.userService = userService;
        this.jwtService=jwtService;
    }

    //  Register new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        String result = userService.register(request);
        return ResponseEntity.ok(result);
    }

    //  Login user
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.login(request);

        
        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

        AuthResponse response = new AuthResponse(token);
        return ResponseEntity.ok(response);
    }
}
