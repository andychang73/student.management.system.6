package com.abstractionizer.studentInformationSystem6.models.bo.user;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginBo {

    @Min(value = 1)
    private Integer userId;

    @NotEmpty
    private String password;
}
