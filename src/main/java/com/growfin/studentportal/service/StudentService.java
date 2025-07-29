package com.growfin.studentportal.service;

import com.growfin.studentportal.exception.CourseNotFoundException;
import com.growfin.studentportal.exception.EnrollmentNotFoundException;
import com.growfin.studentportal.exception.StudentNotFoundException;
import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.entity.Course;
import com.growfin.studentportal.entity.Student;
import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // 1. CREATING A STUDENT

    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = Student.builder()
                .studentName(dto.getStudentName())
                .courses(new HashSet<>())
                .build();

        Student saved = studentRepository.save(student);

        return StudentResponseDTO.builder()
                .studentId(saved.getStudentId())
                .studentName(saved.getStudentName())
                .courseNames(List.of())  // No courses yet
                .build();
    }


    //4. DELETING A STUDENT
    public void deleteStudent(Long id) throws StudentNotFoundException {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    //5. LIST ALL THE COURSES A STUDENT HAS ENROLLED IN
    public List<String> getStudentCourses(Long studentId) throws StudentNotFoundException {
        // check if the student exists before making the search for the courses

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        return student.getCourses().stream()
                .map(Course::getCourseName) // This is valid if courseName is String
                .collect(Collectors.toList());
    }

    //6. ENROLL A STUDENT IN A COURSE
    @Transactional
    public void enrollStudentIntoCourse(Long studentId, Long courseId) throws StudentNotFoundException, CourseNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));

        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
    }

    public void unenrollStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));

        Set<Course> studentCourses = student.getCourses();

        if (!studentCourses.contains(course)) throw new EnrollmentNotFoundException(studentId, courseId);
        studentCourses.remove(course);
        studentRepository.save(student);
    }

}

