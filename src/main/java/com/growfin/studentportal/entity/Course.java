package com.growfin.studentportal.entity;

import com.growfin.studentportal.enums.CourseDifficulty;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long courseId;
    String courseName;

    @Enumerated(EnumType.STRING)
    CourseDifficulty courseDifficulty;

    @ManyToMany(mappedBy = "courses")
    Set<Student> students;
    
}
