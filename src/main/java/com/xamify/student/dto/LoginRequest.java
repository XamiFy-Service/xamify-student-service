package com.xamify.student.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private Integer adminCode;
}
