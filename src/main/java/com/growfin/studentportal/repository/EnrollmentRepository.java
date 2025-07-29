package com.growfin.studentportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Course, Long> {
}
