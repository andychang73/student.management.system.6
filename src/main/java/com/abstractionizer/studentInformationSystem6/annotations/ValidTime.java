package com.abstractionizer.studentInformationSystem6.annotations;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidTime.TimeValidator.class)
public @interface ValidTime {
    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    class TimeValidator implements ConstraintValidator<ValidTime, Time>{
        @Override
        public boolean isValid(Time time, ConstraintValidatorContext constraintValidatorContext) {
            Pattern pattern = Pattern.compile("[0-2][0-9]:[0-5][0-9]:[0-5][0-9]");
            String timeStr = new SimpleDateFormat("HH:mm:ss").format(time);
            return pattern.matcher(timeStr).matches();
        }
    }
}
