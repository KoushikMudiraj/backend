package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.dto.LoginRequest;
import com.agrios.agriosbackend.dto.LoginResponse;
import com.agrios.agriosbackend.dto.RegisterRequest;
import com.agrios.agriosbackend.entity.Role;
import com.agrios.agriosbackend.entity.User;
import com.agrios.agriosbackend.repository.UserRepository;
import com.agrios.agriosbackend.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       org.springframework.security.crypto.password.PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // REGISTER
    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        String encryptedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getName(),
                request.getEmail(),
                encryptedPassword,
                Role.FARMER
        );

        return userRepository.save(user);
    }

    // LOGIN
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(
                token,
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    // TEMPORARY: RESET FARMER PASSWORD
    public void resetFarmerPassword() {

        User user = userRepository
                .findByEmail("farmer@gmail.com")
                .orElseThrow(() ->
                        new RuntimeException("Farmer not found"));

        user.setPassword(
                passwordEncoder.encode("farmer123")
        );

        user.setRole(Role.FARMER);

        userRepository.save(user);
    }
}