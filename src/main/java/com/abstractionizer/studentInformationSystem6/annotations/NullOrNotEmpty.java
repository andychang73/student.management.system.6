package com.abstractionizer.studentInformationSystem6.annotations;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Objects;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NullOrNotEmpty.NullOrNotEmptyValidator.class)
public @interface NullOrNotEmpty {
    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    class NullOrNotEmptyValidator implements ConstraintValidator<NullOrNotEmpty, String>{
        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            return Objects.isNull(s) || s.length() > 0;
        }
    }
}
