package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface ClassService {

    Classes create(Classes classes);

    boolean isAllowedCreateClass(Date date, Date semesterStartDate);

    boolean isTimeConflict(Integer teachId, Integer weekDay, Time startTime, Time endTime);

    ClassesOfTheWeekVo getClassesOfTheWeek(Integer semesterId, Integer courseId);

    ClassInfoVo getClassInfoVo(Integer classId);

    boolean isMyClass(Integer headId, Integer classId);

    boolean isClassMine(Integer id, Integer staffId);

    boolean isClassExists(Integer classId);

    List<ClassVo> getMyClassesOfThisSemester(Integer staffId, Integer semesterId);
}
