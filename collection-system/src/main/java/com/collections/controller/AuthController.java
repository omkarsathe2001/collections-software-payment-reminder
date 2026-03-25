package com.collections.controller;

import com.collections.dto.AuthRequest;
import com.collections.entity.User;
import com.collections.repository.UserRepository;
import com.collections.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository; // ✅ ADD THIS

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        // 🔐 authenticate username + password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 🔥 FETCH USER FROM DB
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 PASS ROLE ALSO
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}