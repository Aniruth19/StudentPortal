package com.growfin.studentportal.controller;
import com.growfin.studentportal.dto.CourseRequestDTO;
import com.growfin.studentportal.dto.CourseResponseDTO;
import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.enums.CourseDifficulty;
import com.growfin.studentportal.service.CourseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.growfin.studentportal.entity.Course;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<String>> getAllCourses() {
        List<String> courses = courseService.getAllCourseNames();
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCourse(@PathVariable  Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/difficulty/{val}")
    ResponseEntity<List<String>> getCourseWithDifficulty(@PathVariable("val") CourseDifficulty courseDifficulty) {
        List<String> courseList = courseService.getCourseWithDifficulty(courseDifficulty);
        return ResponseEntity.ok(courseList);
    }

}
