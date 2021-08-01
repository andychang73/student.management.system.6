package com.abstractionizer.studentInformationSystem6.models.bo.major;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class CreateMajorBo {

    @Min(value = 1)
    private Integer collegeId;

    @NotEmpty(message = "must not be null nor empty")
    private String major;

    @NotEmpty(message = "must not be null nor empty")
    private Set<Integer> courseIds;
}
