package com.xamify.user.repository;

import com.xamify.user.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmailAndPassword(String email, String password);
    Student findByEmail (String email);
}