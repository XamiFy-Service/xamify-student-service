package com.xamify.user.controller;

import com.xamify.user.dto.LoginRequest;
import com.xamify.user.model.Student;
import com.xamify.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("xamify/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Student student) {
        Student savedStudent = studentService.register(student);
        return savedStudent != null ?
                ResponseEntity.ok("Student registered successfully!") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }

    @PostMapping("/login")
    public ResponseEntity<Student> login(@RequestBody LoginRequest loginRequest) {

        Optional<Student> optionalStudent = studentService.login(loginRequest);

        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(optionalStudent.get()); // 200 OK with student data
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable int id) {
        return studentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

