package com.example.spring.services;

import com.example.spring.models.Student;
import com.example.spring.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //@Async
    public List<Student> getStudents() {

        return studentRepository.findAll();
    }

    @Async
    public Student saveStudent(Student student) {
        Student existingUser = studentRepository.findStudentByEmail(student.getEmail());
        if(existingUser != null) {
            throw new IllegalStateException("User with email exists.");
        }

        return studentRepository.saveAndFlush(student);
    }

    @Async
    public void deleteStudent(Long studentId) {
        Optional<Student> existingUser = studentRepository.findById(studentId);

        if(existingUser.isEmpty()) {
            throw new IllegalStateException("Student with id " + studentId + " not found.");
        }

        studentRepository.deleteById(studentId);
    }

    @Async
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
}
