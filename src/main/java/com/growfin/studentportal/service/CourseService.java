package com.growfin.studentportal.service;

import com.growfin.studentportal.dto.CourseRequestDTO;
import com.growfin.studentportal.dto.CourseResponseDTO;
import com.growfin.studentportal.entity.Course;
import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // 2. ADDING A COURSE

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
}
