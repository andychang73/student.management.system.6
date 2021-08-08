package com.abstractionizer.studentInformationSystem6.models.bo.classes;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class GetClassesOfTheWeekBo {

    @Min(value = 0)
    private Integer courseId;
}
