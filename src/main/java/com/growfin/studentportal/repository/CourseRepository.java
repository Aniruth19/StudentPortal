package com.growfin.studentportal.repository;

import com.growfin.studentportal.model.Course;
import com.growfin.studentportal.enums.CourseDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseDifficulty(CourseDifficulty courseDifficulty);

}
