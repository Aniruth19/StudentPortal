package com.growfin.studentportal.repository;

import com.growfin.studentportal.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
