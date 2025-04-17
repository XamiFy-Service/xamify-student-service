package com.xamify.user.service;

import com.xamify.user.dto.LoginRequest;
import com.xamify.user.model.Student;
import java.util.Optional;

public interface StudentService {
    Student register(Student student);
    Optional<Student> login(LoginRequest loginRequest);
    Optional<Student> getById(int id);
}

