package com.abstractionizer.studentInformationSystem6.models.bo.course;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class CreateCourseBo {

    @NotEmpty(message = "must not be null nor empty")
    private String course;

    @Min(value = 1)
    private Integer headId;

    private Set<Integer> preCourses;
}
