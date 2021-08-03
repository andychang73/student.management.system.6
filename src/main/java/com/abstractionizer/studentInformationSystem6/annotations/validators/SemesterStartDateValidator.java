package com.abstractionizer.studentInformationSystem6.annotations.validators;

import com.abstractionizer.studentInformationSystem6.annotations.ValidSemesterStartDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SemesterStartDateValidator implements ConstraintValidator<ValidSemesterStartDate, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return date.after(new Date()) && c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }
}
