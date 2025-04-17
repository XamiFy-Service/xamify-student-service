package com.xamify.user.service;

import com.xamify.user.dto.LoginRequest;
import com.xamify.user.model.Student;
import com.xamify.user.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student register(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> login(LoginRequest loginRequest) {
        return studentRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @Override
    public Optional<Student> getById(int id) {
        return studentRepository.findById(id);
    }
}
