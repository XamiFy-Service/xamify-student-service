package com.xamify.user.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    public String isValidPassword(String password){
        if (password.length() < 8) return "Password must be at least 8 characters long.";
        if (!password.matches(".*[A-Z].*")) return "Password must contain at least one uppercase letter.";
        if (!password.matches(".*[a-z].*")) return "Password must contain at least one lowercase letter.";
        if (!password.matches(".*\\d.*")) return "Password must contain at least one digit.";
        if (!password.matches(".*[@$!%*?&].*")) return "Password must contain at least one special character (@$!%*?&).";
        return null;
    }
}