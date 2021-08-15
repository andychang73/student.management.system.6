package com.abstractionizer.studentInformationSystem6.models.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private Integer studentId;
    private String studentName;
    private Double attendance;
}
