package com.abstractionizer.studentInformationSystem6.models.bo.user;

import com.abstractionizer.studentInformationSystem6.annotations.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@ValidPassword.List({
        @ValidPassword(oldPassword = "oldPassword", newPassword = "newPassword", confirmPassword = "confirmPassword",
        message = "current password must not be same with new password, new password and confirm password must be the same")
})
public class ChangePasswordBo {

    @NotEmpty(message = "must not be empty or null")
    protected String oldPassword;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[~!@#$%^&*()-])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "invalid format")
    @NotEmpty(message = "must not be empty or null")
    protected String newPassword;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()-])(?=\\S+$).{8,}$", message = "invalid format")
    @NotEmpty(message = "must not be empty or null")
    protected String confirmPassword;

}
