package com.xamify.student.service;

import com.xamify.student.dto.LoginRequest;
import com.xamify.student.model.Student;
import com.xamify.student.repository.StudentRepository;
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
