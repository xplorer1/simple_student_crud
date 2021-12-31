package com.example.spring.services;

import com.example.spring.models.Student;
import com.example.spring.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        Student existingUser = studentRepository.findStudentByEmail(student.getEmail());
        if(existingUser != null) {
            throw new IllegalStateException("User with email exists.");
        }

        return studentRepository.saveAndFlush(student);
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> existingUser = studentRepository.findById(studentId);

        if(existingUser.isEmpty()) {
            throw new IllegalStateException("Student with id " + studentId + " not found.");
        }

        studentRepository.deleteById(studentId);
    }
}
