package com.abstractionizer.studentInformationSystem6.utils;

import lombok.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class DateUtil {

    public static Date adjustDate(@NonNull final Date date, @NonNull final Integer numOfDay){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DATE, numOfDay);
        return c.getTime();
    }

    public static Date getFridayOfParticularWeek(@NonNull final Date date, Integer numOfDay){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        if(Objects.nonNull(numOfDay)){
            c.add(Calendar.DATE, numOfDay);
        }
        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return c.getTime();
    }

    public static Date getDateByWeekDay(@NonNull final Date date, @NonNull final Integer weekDay){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, weekDay);
        return c.getTime();
    }

    public static Date addDaysToDate(@NonNull final Date date, @NonNull final Integer numOfDay){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_WEEK, numOfDay);
        return c.getTime();
    }
}
