package com.abstractionizer.studentInformationSystem6.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static Date adjustDate(Date date, Integer numOfDay){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DATE, numOfDay);
        return c.getTime();
    }

    public static Date getFridayOfParticularWeek(Date date, Integer numOfDay){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DATE, numOfDay);
        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return c.getTime();
    }

    public static Date getDateByWeekDay(Date date, Integer weekDay){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, weekDay);
        return c.getTime();
    }

    public static Date addDaysToDate(Date date, Integer numOfDay){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_WEEK, numOfDay);
        return c.getTime();
    }
}
