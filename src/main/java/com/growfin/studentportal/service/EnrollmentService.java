package com.growfin.studentportal.service;

import com.growfin.studentportal.enums.CourseStatus;

import java.time.LocalDate;
import java.util.List;

public interface EnrollmentService {
    void enrollStudent(Long studentId, Long courseId);

    void unenrollStudent(Long studentId, Long courseId);

    List<String> getCompletedCoursesForStudent(Long studentId);

    List<String> getActiveEnrollmentsForStudent(Long studentId);

    List<String> getEnrollmentAfterDate(LocalDate date);

    void setStudentEnrollmentStatus(Long studentId, Long courseId, CourseStatus status);
}
