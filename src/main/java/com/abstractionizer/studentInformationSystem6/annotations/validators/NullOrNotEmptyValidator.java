package com.abstractionizer.studentInformationSystem6.annotations.validators;

import com.abstractionizer.studentInformationSystem6.annotations.NullOrNotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NullOrNotEmptyValidator implements ConstraintValidator<NullOrNotEmpty, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(s) || s.length() > 0;
    }
}
