package com.growfin.studentportal.service;
import java.util.*;
import java.util.stream.Collectors;

import com.growfin.studentportal.dto.CourseRequestDTO;
import com.growfin.studentportal.dto.CourseResponseDTO;
import com.growfin.studentportal.entity.Course;
import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // 2. ADDING A COURSE

    @Transactional
    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        String courseName = dto.getCourseName();

        Course course = Course.builder()
                .courseName(courseName)
                .build();

        Course saved = courseRepository.save(course);

        return CourseResponseDTO.builder()
                .courseId(saved.getCourseId())
                .courseName(saved.getCourseName())
                .build();
    }

    // 3. LIST ALL THE COURSES
    public List<String> getAllCourseNames() {
        return courseRepository.findAll().stream()
                .map(Course::getCourseName)
                .toList();
    }


}
