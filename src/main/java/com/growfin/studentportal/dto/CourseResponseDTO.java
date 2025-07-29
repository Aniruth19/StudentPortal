package com.growfin.studentportal.dto;
import lombok.*;
import java.util.List;
import com.growfin.studentportal.enums.CourseDifficulty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {
    private Long courseId;
    private String courseName;
    private String courseDifficulty;
    private List<String> studentNames;
}
