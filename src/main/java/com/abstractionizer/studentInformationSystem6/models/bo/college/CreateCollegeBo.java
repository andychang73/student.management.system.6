package com.abstractionizer.studentInformationSystem6.models.bo.college;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateCollegeBo {

    @NotEmpty
    private String college;
}
