package com.growfin.studentportal.model;
import com.growfin.studentportal.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private LocalDate enrolledAt;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;
}
