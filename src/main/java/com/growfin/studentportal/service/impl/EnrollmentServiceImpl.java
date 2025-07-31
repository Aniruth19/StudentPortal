package com.growfin.studentportal.service.impl;

import com.growfin.studentportal.enums.CourseStatus;
import com.growfin.studentportal.exception.CourseNotFoundException;
import com.growfin.studentportal.exception.EnrollmentNotFoundException;
import com.growfin.studentportal.exception.StudentNotFoundException;
import com.growfin.studentportal.model.Course;
import com.growfin.studentportal.model.Enrollment;
import com.growfin.studentportal.model.Student;
import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.repository.EnrollmentRepository;
import com.growfin.studentportal.repository.StudentRepository;
import com.growfin.studentportal.service.EnrollmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public void enrollStudent(Long studentId, Long courseId) throws StudentNotFoundException, CourseNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDate.now())
                .courseStatus(CourseStatus.ENROLLED)
                .build();

        enrollmentRepository.save(enrollment);
    }

    @Override
    public void unenrollStudent(Long studentId, Long courseId) throws EnrollmentNotFoundException, StudentNotFoundException {
        Enrollment enrollment = enrollmentRepository.findByStudentStudentIdAndCourseCourseId(studentId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException(studentId, courseId));
        enrollmentRepository.delete(enrollment);
    }

    @Override
    public List<String> getCompletedCoursesForStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        return enrollmentRepository.findByStudentStudentIdAndCourseStatus(studentId, CourseStatus.COMPLETED)
                .stream()
                .map(e -> e.getCourse().getCourseName())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getActiveEnrollmentsForStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        return enrollmentRepository.findByStudentStudentIdAndCourseStatus(studentId, CourseStatus.ENROLLED)
                .stream()
                .map(e -> e.getCourse().getCourseName())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEnrollmentAfterDate(LocalDate date) {
        return enrollmentRepository.findByEnrolledAtAfter(date)
                .stream()
                .map(e -> e.getStudent().getStudentName())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void setStudentEnrollmentStatus(Long studentId, Long courseId, CourseStatus status) throws EnrollmentNotFoundException {
        Enrollment enrollment = enrollmentRepository
                .findByStudentStudentIdAndCourseCourseId(studentId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException(studentId, courseId));
        enrollment.setCourseStatus(status);
        enrollmentRepository.save(enrollment);
    }
}
