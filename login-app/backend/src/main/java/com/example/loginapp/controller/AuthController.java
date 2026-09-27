package com.example.loginapp.controller;

import com.example.loginapp.dto.ApiResponse;
import com.example.loginapp.dto.LoginRequest;
import com.example.loginapp.entity.User;
import com.example.loginapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // tighten this to your actual frontend URL in production
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Invalid username or password."));
        }

        return ResponseEntity.ok(new ApiResponse("Login successful!"));
    }

    // Simple endpoint to register a user, useful for testing
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody LoginRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("Username already exists."));
        }

        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User registered successfully."));
    }
}
