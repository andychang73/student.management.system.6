package com.abstractionizer.studentInformationSystem6.annotations;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidSemesterStartDate.SemesterStartDateValidator.class)
public @interface ValidSemesterStartDate {
    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    class SemesterStartDateValidator implements ConstraintValidator<ValidSemesterStartDate, Date>{
        @Override
        public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
            Calendar c = new GregorianCalendar();
            c.setTime(date);
            return date.after(new Date()) && c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
        }
    }
}
