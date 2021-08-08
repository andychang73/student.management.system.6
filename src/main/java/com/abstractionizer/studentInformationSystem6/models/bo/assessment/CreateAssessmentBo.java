package com.abstractionizer.studentInformationSystem6.models.bo.assessment;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

@Data
public class CreateAssessmentBo {

    @Min(value = 1)
    private Integer staffId;

    @Range(min = 1, max = 100)
    private Float result;
}
