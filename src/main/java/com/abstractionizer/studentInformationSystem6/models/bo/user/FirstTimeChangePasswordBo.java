package com.abstractionizer.studentInformationSystem6.models.bo.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FirstTimeChangePasswordBo extends ChangePasswordBo{
    private Integer userId;
}
