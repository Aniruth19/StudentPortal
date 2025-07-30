package com.growfin.studentportal.controller;

import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.service.EnrollmentService;
import com.growfin.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.growfin.studentportal.enums.CourseStatus;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private EnrollmentService enrollmentService;

    // 1. CREATE A STUDENT
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO dto) {
        StudentResponseDTO response = studentService.createStudent(dto);
        return ResponseEntity.ok(response);
    }

    // 2. DELETE A STUDENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // 3. LIST ALL COURSES A STUDENT IS ENROLLED IN (ANY STATUS)
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<String>> getStudentCourses(@PathVariable Long id) {
        List<String> studentCourses = studentService.getStudentCourses(id);
        return ResponseEntity.ok(studentCourses);
    }

}