package com.abstractionizer.studentInformationSystem6.models.dto.assessment;

import lombok.Data;

@Data
public class AssessmentDto {
    private Integer year;
    private Integer semester;
    private Float result;
}
