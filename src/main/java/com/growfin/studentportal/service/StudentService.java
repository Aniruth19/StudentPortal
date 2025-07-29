package com.growfin.studentportal.service;

import com.growfin.studentportal.exception.CourseNotFoundException;
import com.growfin.studentportal.exception.StudentNotFoundException;
import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.entity.Course;
import com.growfin.studentportal.entity.Student;
import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

        Set<Course> selectedCourses = new HashSet<>(courseRepository.findAllById(dto.getCourseIds()));

        Student student = Student.builder()
                .studentName(dto.getStudentName())
                .courses(selectedCourses)
                .build();

        Student saved = studentRepository.save(student);

        List<String> courseNames = saved.getCourses().stream()
                .map(Course::getCourseName)
                .toList();

        return StudentResponseDTO.builder()
                .studentId(saved.getStudentId())
                .studentName(saved.getStudentName())
                .courseNames(courseNames)
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
                .map(Course::getCourseName)
                .collect(Collectors.toList());
    }

    //6. ENROLL A STUDENT IN A COURSE

    //GET STUDENT ID
    //GET COURSE ID
    //ADD STUDENT -> COURSES -> ADD SELECTED COURSE WITH ITS COURSE ID
    public void enrollStudentIntoCourse(Long studentId, Long courseId) throws StudentNotFoundException, CourseNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        //got both student and course what next?
        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
    }


}

