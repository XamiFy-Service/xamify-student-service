package com.xamify.user.service;

import com.xamify.user.dto.AdminResponse;
import com.xamify.user.dto.LoginRequest;
import com.xamify.user.dto.XamiFyResponse;
import com.xamify.user.model.Admin;
import com.xamify.user.repository.AdminRepository;
import com.xamify.user.util.PasswordValidator;
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

    @Autowired
    private PasswordValidator passwordValidator;

    @Override
    public ResponseEntity<XamiFyResponse<AdminResponse>> registerAdmin(Admin admin) {
        String errorMessage = passwordValidator.isValidPassword(admin.getPassword());
        if(errorMessage != null){
            XamiFyResponse<AdminResponse> response = new XamiFyResponse<>();
            response.setStatus("Error ❌");
            response.setMessage(errorMessage);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        admin.setAdminCode(adminCode);
        adminRepository.save(admin);

        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setAdminId(admin.getAdminId());
        adminResponse.setName(admin.getName());
        adminResponse.setEmail(admin.getEmail());

        XamiFyResponse<AdminResponse> response = new XamiFyResponse<>();
        response.setStatus("Success ✅");
        response.setMessage("Admin added successfully");
        response.setData(adminResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
