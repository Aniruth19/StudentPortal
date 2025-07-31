package com.growfin.studentportal.service;

import com.growfin.studentportal.dto.CourseRequestDTO;
import com.growfin.studentportal.dto.CourseResponseDTO;
import com.growfin.studentportal.enums.CourseDifficulty;

import java.util.List;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO dto);

    List<String> getAllCourseNames();

    void deleteCourse(Long courseId);

    List<String> getCourseWithDifficulty(CourseDifficulty courseDifficulty);
}
