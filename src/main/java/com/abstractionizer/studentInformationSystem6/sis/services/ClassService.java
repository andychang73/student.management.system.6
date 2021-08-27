package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentAverageGradesVo;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClassService {

    Classes create(Classes classes);

    boolean isAllowedCreateClass(Date date, Date semesterStartDate);

    boolean isTimeConflict(Integer teachId, Integer weekDay, Time startTime, Time endTime);

    ClassesOfTheWeekVo getClassesOfTheWeek(Integer semesterId, Integer courseId);

    ClassInfoVo getClassInfoVo(Integer classId);

    boolean isMyClass(Integer headId, Integer classId);

    boolean isClassValid(Integer id, Integer staffId, Date date);

    List<ClassVo> getMyClassesOfThisSemester(Integer staffId, Integer semesterId);

    Integer getNumberOfHomework(Integer id);

    List<StudentAverageGradesVo> getStudentAverageGrades(Integer classId, Integer numberOfHomework);

    List<StudentCourse> areStudentsInThisClass(Integer classId, Integer semesterId, Set<Integer> studentIds);

    Optional<StudentCourse> isStudentInThisClass(Integer classId, Integer semesterId, Integer studentId);
}
