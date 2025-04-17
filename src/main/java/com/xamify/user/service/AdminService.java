package com.xamify.user.service;

import com.xamify.user.dto.AdminResponse;
import com.xamify.user.dto.LoginRequest;
import com.xamify.user.dto.XamiFyResponse;
import com.xamify.user.model.Admin;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<XamiFyResponse<AdminResponse>> registerAdmin (Admin admin);
    ResponseEntity<XamiFyResponse<AdminResponse>> adminLogin (LoginRequest loginRequest);
}