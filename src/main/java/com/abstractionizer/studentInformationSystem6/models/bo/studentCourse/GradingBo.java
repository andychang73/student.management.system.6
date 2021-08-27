package com.abstractionizer.studentInformationSystem6.models.bo.studentCourse;

import lombok.Data;
import javax.validation.constraints.Min;

@Data
public class GradingBo {

    @Min(value = 1, message = "minimum value is 1")
    private Integer classId;

    @Min(value = 1, message = "minimum value is 1")
    private Integer studentId;

    @Min(value = 0, message = "minimum value is 0")
    private Float grade;

}
