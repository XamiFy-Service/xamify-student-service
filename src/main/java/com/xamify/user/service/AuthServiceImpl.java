package com.xamify.user.service;

import com.xamify.user.repository.AdminRepository;
import com.xamify.user.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    public String validateToken(String token) {
        String email = extractEmailFromToken(token);
        if (isValidStudent(email)) {
            return "STUDENT";
        }
        if (isValidAdmin(email)) {
            return "ADMIN";
        }

        return "INVALID";
    }

    public boolean isValidStudent(String email) {
        return studentRepository.findByEmail(email) != null;
    }


    public boolean isValidAdmin(String email) {
        return adminRepository.findByEmail(email) != null;
    }

    public String extractEmailFromToken(String token) {
        // In real applications, you would decode the JWT token to extract the email
        // Here, for simplicity, let's assume the token is the email
        return token;
    }
}
