package com.abstractionizer.studentInformationSystem6.annotations;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidAnswer.ValidAnswerValidator.class)
public @interface ValidAnswer {
    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    class ValidAnswerValidator implements ConstraintValidator<ValidAnswer, String> {

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            return s.equals("a") || s.equals("b") || s.equals("c") || s.equals("d");
        }
    }
}
