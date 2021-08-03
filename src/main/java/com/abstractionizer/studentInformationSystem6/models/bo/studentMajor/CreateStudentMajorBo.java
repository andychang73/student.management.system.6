package com.abstractionizer.studentInformationSystem6.models.bo.studentMajor;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CreateStudentMajorBo {

    @Min(value = 1, message = "minimum value is 1")
    private Integer studentId;

    @Min(value = 1, message =  "minimum value is 1")
    private Integer majorId;
}
