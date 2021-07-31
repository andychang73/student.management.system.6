package com.abstractionizer.studentInformationSystem6.models.bo.user;

import com.abstractionizer.studentInformationSystem6.annotations.NullOrNotEmpty;
import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
public class UpdateUserInfo {

    @NullOrNotEmpty(message = "can be null but not empty")
    @Pattern(regexp = "^(.*)@(.*)$", message = "invalid email format")
    private String email;

    @NullOrNotEmpty(message = "can be null but not empty")
    @Pattern(regexp = "09\\d{8}", message = "invalid phone format")
    private String phone;

    @NullOrNotEmpty(message = "can be null but not empty")
    private String address;
}
