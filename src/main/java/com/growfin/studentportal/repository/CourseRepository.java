package com.growfin.studentportal.repository;

import com.growfin.studentportal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
