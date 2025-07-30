package com.growfin.studentportal.repository;

import com.growfin.studentportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
