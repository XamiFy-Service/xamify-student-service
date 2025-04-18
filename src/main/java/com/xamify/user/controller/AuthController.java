package com.xamify.user.controller;

import com.xamify.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " from the token
        String tokenWithoutBearer = token.startsWith("Bearer ") ? token.substring(7) : token;

        // Validate the token and get the user role (Admin or Student)
        String role = authService.validateToken(tokenWithoutBearer);

        if ("INVALID".equals(role)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        return ResponseEntity.ok(role);  // Return the role as response
    }
}
