package com.xamify.student.service;

import com.xamify.student.dto.AdminResponse;
import com.xamify.student.dto.LoginRequest;
import com.xamify.student.dto.XamiFyResponse;
import com.xamify.student.model.Admin;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<XamiFyResponse> registerAdmin (Admin admin);
    ResponseEntity<XamiFyResponse<AdminResponse>> adminLogin (LoginRequest loginRequest);
}