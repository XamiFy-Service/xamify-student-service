package com.xamify.user.service;

public interface AuthService {
    String validateToken(String token);
    boolean isValidStudent(String email);
    boolean isValidAdmin(String email);
    String extractEmailFromToken(String token);
}
