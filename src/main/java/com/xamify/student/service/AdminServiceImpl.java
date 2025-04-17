package com.xamify.student.service;

import com.xamify.student.dto.AdminResponse;
import com.xamify.student.dto.LoginRequest;
import com.xamify.student.dto.XamiFyResponse;
import com.xamify.student.model.Admin;
import com.xamify.student.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Value("${admin.code}")
    private int adminCode;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private XamiFyResponse xamiFyResponse;

    @Override
    public ResponseEntity<XamiFyResponse> registerAdmin(Admin admin) {
        admin.setAdminCode(adminCode);
        adminRepository.save(admin);
        xamiFyResponse.setStatus("Success ✅");
        xamiFyResponse.setMessage("Admin added successfully");
        xamiFyResponse.setData(admin);
        return new ResponseEntity<>(xamiFyResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<XamiFyResponse<AdminResponse>> adminLogin(LoginRequest loginRequest) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if(optionalAdmin.isPresent() && loginRequest.getAdminCode() == adminCode){
            String adminName = optionalAdmin.get().getName();
            Admin admin = optionalAdmin.get();

            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setAdminId(admin.getAdminId());
            adminResponse.setName(admin.getName());
            adminResponse.setEmail(admin.getEmail());

            XamiFyResponse<AdminResponse> response = new XamiFyResponse<>();
            response.setStatus("Logged In ✅");
            response.setMessage("Welcome Back " + adminName);
            response.setData(adminResponse);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        XamiFyResponse<AdminResponse> response = new XamiFyResponse<>();
        response.setStatus("Failed 🚫");
        response.setMessage("Please check your credentials and Try again");
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }
}
