package com.growfin.studentportal.dto;
import lombok.*;
import com.growfin.studentportal.enums.CourseDifficulty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {
    private String courseName;
    private CourseDifficulty courseDifficulty;
}
