package com.growfin.studentportal.controller;

import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.enums.CourseStatus;
import com.growfin.studentportal.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.growfin.studentportal.service.EnrollmentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Void> enrollStudent(@RequestParam Long studentId,
                                              @RequestParam Long courseId) {
        enrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unenrollStudent(@RequestParam Long studentId,
                                                @RequestParam Long courseId) {
        enrollmentService.unenrollStudent(studentId, courseId);
        return ResponseEntity.noContent().build();
    }

    // to get all the courses that the student has completed
    @GetMapping("/student/{id}/completed")
    public ResponseEntity<List<String>> getCompletedCourses(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getCompletedCoursesForStudent(id));
    }

    @GetMapping("/student/{id}/active")
    public ResponseEntity<List<String>> getActiveCourses(@PathVariable Long id){
        List<String> response = enrollmentService.getActiveEnrollmentsForStudent(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{date}/enrollment")
    public ResponseEntity<List<String>> getEnrollmentsAfterDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<String> response = enrollmentService.getEnrollmentAfterDate(localDate);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-status")
    public ResponseEntity<Void> updateEnrollmentStatus(@RequestParam Long studentId,
                                                       @RequestParam Long courseId,
                                                       @RequestParam CourseStatus status) {
        enrollmentService.setStudentEnrollmentStatus(studentId, courseId, status);
        return ResponseEntity.noContent().build();
    }

}

