package com.abstractionizer.studentInformationSystem6.models.bo.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Data
public class CreateStudentBo {

    @NotEmpty
    protected String username;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date birthday;

    @NotEmpty(message = "cannot be null nor empty")
    @Pattern(regexp = "^(.*)@(.*)$", message = "invalid format")
    protected String email;

    @NotEmpty(message = "cannot be null nor empty")
    @Pattern(regexp = "^09\\d{8}", message = "invalid format")
    protected String phone;

    @NotEmpty(message = "cannot be null nor empty")
    protected String address;
}
