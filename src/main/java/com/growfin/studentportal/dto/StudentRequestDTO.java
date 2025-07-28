package com.growfin.studentportal.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDTO {
    private String studentName;
    private List<Long> courseIds;
}
