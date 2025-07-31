package com.growfin.studentportal.service.impl;

import com.growfin.studentportal.dto.CourseRequestDTO;
import com.growfin.studentportal.dto.CourseResponseDTO;
import com.growfin.studentportal.enums.CourseDifficulty;
import com.growfin.studentportal.exception.CourseNotFoundException;
import com.growfin.studentportal.model.Course;
import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = Course.builder()
                .courseName(dto.getCourseName())
                .courseDifficulty(dto.getCourseDifficulty())
                .build();

        Course saved = courseRepository.save(course);

        return CourseResponseDTO.builder()
                .courseId(saved.getCourseId())
                .courseName(saved.getCourseName())
                .courseDifficulty(saved.getCourseDifficulty().toString())
                .build();
    }

    @Override
    public List<String> getAllCourseNames() {
        return courseRepository.findAll().stream()
                .map(Course::getCourseName)
                .toList();
    }

    @Override
    public void deleteCourse(Long courseId) throws  CourseNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
        courseRepository.delete(course);
    }

    @Override
    public List<String> getCourseWithDifficulty(CourseDifficulty courseDifficulty) {
        return courseRepository.findByCourseDifficulty(courseDifficulty).stream()
                .map(Course::getCourseName)
                .toList();
    }
}
