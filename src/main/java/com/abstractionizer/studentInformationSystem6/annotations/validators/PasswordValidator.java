package com.abstractionizer.studentInformationSystem6.annotations.validators;

import com.abstractionizer.studentInformationSystem6.annotations.ValidPassword;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, Object> {

    @SneakyThrows
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        final String oldPassword = BeanUtils.getProperty(o, "oldPassword");
        final String newPassword = BeanUtils.getProperty(o, "newPassword");
        final String confirmPassword = BeanUtils.getProperty(o, "confirmPassword");
        return !oldPassword.equals(newPassword) && newPassword.equals(confirmPassword);
    }
}
