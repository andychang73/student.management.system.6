package com.abstractionizer.studentInformationSystem6.annotations;

import com.abstractionizer.studentInformationSystem6.annotations.validators.TimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TimeValidator.class)
public @interface ValidTime {
    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
