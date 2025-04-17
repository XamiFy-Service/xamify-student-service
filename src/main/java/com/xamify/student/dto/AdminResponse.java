package com.xamify.student.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AdminResponse {
    private int adminId;
    private String name;
    private String email;
}
