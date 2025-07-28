package com.growfin.studentportal.dto;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {
    private Long studentId;
    private String studentName;
    private List<String> courseNames;
}
