package com.growfin.studentportal.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long studentId;
    String studentName;

    @ManyToMany(cascade = CascadeType.ALL )
            @JoinTable(
                    name = "student_courses",
                    joinColumns = @JoinColumn(name = "student_id"),
                    inverseJoinColumns = @JoinColumn(name = "course_id")
            )
    Set<Course> courses;


}
