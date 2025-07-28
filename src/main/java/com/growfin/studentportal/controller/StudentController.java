package com.growfin.studentportal.controller;

import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.entity.Student;
import com.growfin.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO dto) {
        StudentResponseDTO response = studentService.createStudent(dto);
        return ResponseEntity.ok(response);
    }
}


