package com.growfin.studentportal.service;

import com.growfin.studentportal.dto.CourseRequestDTO;
import com.growfin.studentportal.dto.CourseResponseDTO;
import com.growfin.studentportal.model.Course;
import com.growfin.studentportal.enums.CourseDifficulty;
import com.growfin.studentportal.exception.CourseNotFoundException;
import com.growfin.studentportal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // 1. CREATE COURSE
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

    // 2. LIST ALL COURSE NAMES
    public List<String> getAllCourseNames() {
        return courseRepository.findAll().stream()
                .map(Course::getCourseName)
                .toList();
    }

    // 3. DELETE COURSE
    public void deleteCourse(Long courseId) throws CourseNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
        courseRepository.delete(course);
    }

    // 4. FILTER BY DIFFICULTY
    public List<String> getCourseWithDifficulty(CourseDifficulty courseDifficulty) {
        return courseRepository.findByCourseDifficulty(courseDifficulty).stream()
                .map(Course::getCourseName)
                .toList();
    }
}
