package com.growfin.studentportal.service;

import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.model.Course;
import com.growfin.studentportal.model.Enrollment;
import com.growfin.studentportal.model.Student;
import com.growfin.studentportal.enums.CourseStatus;
import com.growfin.studentportal.exception.CourseNotFoundException;
import com.growfin.studentportal.exception.EnrollmentNotFoundException;
import com.growfin.studentportal.exception.StudentNotFoundException;
import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.repository.EnrollmentRepository;
import com.growfin.studentportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // ENROLL STUDENT
    public void enrollStudent(Long studentId, Long courseId) {
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

    // UNENROLL STUDENT
    public void unenrollStudent(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentStudentIdAndCourseCourseId(studentId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException(studentId, courseId));

        enrollmentRepository.delete(enrollment);
    }

    // GET COMPLETED COURSES
    public List<String> getCompletedCoursesForStudent(Long studentId) throws StudentNotFoundException {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        List<Enrollment> completedEnrollments = enrollmentRepository
                .findByStudentStudentIdAndCourseStatus(studentId, CourseStatus.COMPLETED);

        return completedEnrollments.stream()
                .map(e -> e.getCourse().getCourseName())
                .collect(Collectors.toList());
    }

    //getting the active enrollments for a student
    public List<String> getActiveEnrollmentsForStudent(Long studentId) throws StudentNotFoundException {
        if(!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        List<Enrollment> completedEnrollments = enrollmentRepository
                .findByStudentStudentIdAndCourseStatus(studentId, CourseStatus.ENROLLED);

        return completedEnrollments.stream()
                .map(e->e.getCourse().getCourseName())
                .collect(Collectors.toList());
    }

    //getEnrollmentsAfterDate
    public List<String> getEnrollmentAfterDate(LocalDate date) {
        return enrollmentRepository.findByEnrolledAtAfter(date).stream()
                .map(e -> e.getStudent().getStudentName())
                .distinct()
                .collect(Collectors.toList());
    }

    public void setStudentEnrollmentStatus(Long studentId, Long courseId, CourseStatus status) {

        Enrollment enrollment = enrollmentRepository
                .findByStudentStudentIdAndCourseCourseId(studentId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException(studentId, courseId));

        enrollment.setCourseStatus(status);
        enrollmentRepository.save(enrollment);
    }

}

