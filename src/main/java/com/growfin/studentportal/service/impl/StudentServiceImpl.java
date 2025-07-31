package com.growfin.studentportal.service.impl;

import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.dto.StudentResponseDTO;
import com.growfin.studentportal.exception.StudentNotFoundException;
import com.growfin.studentportal.model.Enrollment;
import com.growfin.studentportal.model.Student;
import com.growfin.studentportal.repository.EnrollmentRepository;
import com.growfin.studentportal.repository.StudentRepository;
import com.growfin.studentportal.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = Student.builder()
                .studentName(dto.getStudentName())
                .build();

        Student saved = studentRepository.save(student);

        return StudentResponseDTO.builder()
                .studentId(saved.getStudentId())
                .studentName(saved.getStudentName())
                .courseNames(List.of())
                .build();
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<String> getStudentCourses(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        List<Enrollment> enrollments = enrollmentRepository.findByStudentStudentId(studentId);
        return enrollments.stream()
                .map(e -> e.getCourse().getCourseName())
                .collect(Collectors.toList());
    }
}
