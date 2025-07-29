package com.growfin.studentportal.controller;

import java.util.*;
import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.entity.Student;
import com.growfin.studentportal.exception.StudentNotFoundException;
import com.growfin.studentportal.repository.StudentRepository;
import com.growfin.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO dto) {
        StudentResponseDTO response = studentService.createStudent(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<String>> getStudentCourses(@PathVariable Long id) {
        List<String> studentCourses = studentService.getStudentCourses(id);
        return ResponseEntity.ok(studentCourses);
    }

    // student {id} courses {id}
    @PutMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> enrollStudentIntoCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        studentService.enrollStudentIntoCourse(studentId, courseId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

}


