package com.abstractionizer.studentInformationSystem6.models.bo.user;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class CreateStaffBo extends CreateStudentBo {

    @Min(value = 1)
    private Integer reportTo;

    @NotEmpty
    private Set<Integer> roleIds;
}
