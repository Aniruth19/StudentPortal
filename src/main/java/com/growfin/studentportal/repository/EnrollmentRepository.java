package com.growfin.studentportal.repository;

import com.growfin.studentportal.model.Enrollment;
import com.growfin.studentportal.enums.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByStudentStudentIdAndCourseCourseId(Long studentId, Long courseId);
    List<Enrollment> findByStudentStudentIdAndCourseStatus(Long studentId, CourseStatus status);
    List<Enrollment> findByStudentStudentId(Long studentId);
    List<Enrollment> findByEnrolledAtAfter(LocalDate date);
}

