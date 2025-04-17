package com.xamify.user.controller;

import com.xamify.user.dto.AdminResponse;
import com.xamify.user.dto.LoginRequest;
import com.xamify.user.dto.XamiFyResponse;
import com.xamify.user.model.Admin;
import com.xamify.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xamify/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    private ResponseEntity<XamiFyResponse<AdminResponse>> registerAdmin(@RequestBody Admin admin){
        return adminService.registerAdmin(admin);
    }

    @PostMapping("/login")
    private ResponseEntity<XamiFyResponse<AdminResponse>> adminLogin(@RequestBody LoginRequest loginRequest) {
        return adminService.adminLogin(loginRequest);
    }

}
