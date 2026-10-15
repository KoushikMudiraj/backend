package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.LoginRequest;
import com.agrios.agriosbackend.dto.LoginResponse;
import com.agrios.agriosbackend.dto.RegisterRequest;
import com.agrios.agriosbackend.entity.User;
import com.agrios.agriosbackend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}