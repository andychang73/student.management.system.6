package com.abstractionizer.studentInformationSystem6.models.bo.studentClass;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class EnrollBo {

    @Min(value = 0)
    private Integer classId;
}
