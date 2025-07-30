package com.growfin.studentportal.model;

import com.growfin.studentportal.enums.CourseDifficulty;

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
    private Long courseId;
    private String courseName;

    @Enumerated(EnumType.STRING)
    private CourseDifficulty courseDifficulty;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments;
    
}
