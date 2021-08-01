package com.abstractionizer.studentInformationSystem6.models.bo.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class CreateRoleBo {

    @NotEmpty(message = "must not be null or empty")
    @Pattern(regexp = "^ROLE_.*$", message = "invalid prefix")
    private String role;

    @NotEmpty(message = "must not be null or empty")
    private Set<Integer> authorityIds;
}
