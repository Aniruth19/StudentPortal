package com.growfin.studentportal.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {
    private Long courseId;
    private String courseName;
    private List<String> studentNames;
}
