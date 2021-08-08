package com.abstractionizer.studentInformationSystem6.annotations.validators;

import com.abstractionizer.studentInformationSystem6.annotations.ValidTime;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

@Slf4j
public class TimeValidator implements ConstraintValidator<ValidTime, Time> {

    @Override
    public boolean isValid(Time time, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("[0-2][0-9]:[0-5][0-9]:[0-5][0-9]");
        String timeStr = new SimpleDateFormat("HH:mm:ss").format(time);
        return pattern.matcher(timeStr).matches();
    }
}
