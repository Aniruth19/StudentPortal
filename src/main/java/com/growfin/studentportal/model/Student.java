package com.growfin.studentportal.model;
import jakarta.persistence.*;
import java.util.*;
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
    private long studentId;
    private String studentName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments;

}
