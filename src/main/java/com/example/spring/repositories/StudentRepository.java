package com.example.spring.repositories;

import com.example.spring.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByEmail(String email);
}