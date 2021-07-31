package com.abstractionizer.studentInformationSystem6.models.bo.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class CreateRoleBo {

    @NotEmpty(message = "must not be null or empty")
    private String role;

    @NotEmpty(message = "must not be null or empty")
    private Set<Integer> authorityIds;
}
