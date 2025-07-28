package com.growfin.studentportal.service;

import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.entity.Course;
import com.growfin.studentportal.entity.Student;
import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }



}

