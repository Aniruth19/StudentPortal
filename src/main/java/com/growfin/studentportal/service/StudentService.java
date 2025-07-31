package com.growfin.studentportal.service;

import com.growfin.studentportal.dto.StudentRequestDTO;
import com.growfin.studentportal.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO dto);

    void deleteStudent(Long id);

    List<String> getStudentCourses(Long studentId);
}
