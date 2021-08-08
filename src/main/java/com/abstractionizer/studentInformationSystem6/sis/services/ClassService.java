package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import java.sql.Time;
import java.util.Date;

public interface ClassService {

    Classes create(Classes classes);

    boolean isAllowedCreateClass(Date date, Date semesterStartDate);

    boolean isTimeConflict(Integer teachId, Integer weekDay, Time startTime, Time endTime);

    ClassesOfTheWeekVo getClassesOfTheWeek(Integer semesterId, Integer courseId);

    ClassInfoVo getClassInfoVo(Integer classId);

    boolean isMyClass(Integer headId, Integer classId);
}
