package com.growfin.studentportal.service;

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



}
