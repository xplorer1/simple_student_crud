package com.example.spring.controllers;

import com.example.spring.models.Student;
import com.example.spring.services.StudentService;
import com.example.spring.utils.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<AppResponse> getStudents() {

        return ResponseEntity.ok(
            AppResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(OK)
                .data(Map.of("students", studentService.getStudents()))
                .build()
        );
    }

    @PostMapping
    public ResponseEntity<AppResponse> saveStudent(@RequestBody Student student) {
        studentService.saveStudent( student );

        return ResponseEntity.ok(
            AppResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(OK)
                .message("Student saved successfully.")
                .build()
        );
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<AppResponse> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);

        return ResponseEntity.ok(
            AppResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(OK)
                .message("Student deleted successfully.")
                .build()
        );
    }

    @PutMapping
    public ResponseEntity<AppResponse> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);

        return ResponseEntity.ok(
            AppResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(OK)
                .message("Student updated successfully.")
                .build()
        );
    }
}
