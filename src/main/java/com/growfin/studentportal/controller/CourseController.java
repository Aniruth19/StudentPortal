package com.growfin.studentportal.controller;
import com.growfin.studentportal.dto.CourseRequestDTO;
import com.growfin.studentportal.dto.CourseResponseDTO;
import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.growfin.studentportal.entity.Course;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO dto) {
        CourseResponseDTO  response = courseService.createCourse(dto);
        return ResponseEntity.ok(response);
    }
}
