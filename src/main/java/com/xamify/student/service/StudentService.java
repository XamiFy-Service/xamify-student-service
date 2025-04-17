package com.xamify.student.service;

import com.xamify.student.dto.LoginRequest;
import com.xamify.student.model.Student;
import java.util.Optional;

public interface StudentService {
    Student register(Student student);
    Optional<Student> login(LoginRequest loginRequest);
    Optional<Student> getById(int id);
}

