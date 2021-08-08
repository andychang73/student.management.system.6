package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.ClassMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import com.abstractionizer.studentInformationSystem6.sis.services.ClassService;
import com.abstractionizer.studentInformationSystem6.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassMapper classMapper;

    @Override
    public Classes create(@NonNull final Classes classes) {
        if(classMapper.insert(classes) != 1){
            log.error("Failed to create class: {}", classes);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return classes;
    }

    @Override
    public boolean isAllowedCreateClass(@NonNull final Date now, @NonNull final Date semesterStartDate) {
        return !now.before(DateUtil.adjustDate(semesterStartDate, -30)) && !now.after(DateUtil.adjustDate(semesterStartDate, -15));
    }

    @Override
    public boolean isTimeConflict(@NonNull final Integer teachId, @NonNull final Integer weekDay, @NonNull final Time startTime, @NonNull final Time endTime) {
        return classMapper.countByStaffIdAndStartTimeAndEndTime(teachId, weekDay, startTime, endTime) > 0;
    }

    @Override
    public ClassesOfTheWeekVo getClassesOfTheWeek(@NonNull final Integer semesterId, @NonNull final Integer courseId) {
        return classMapper.selectClassDtoBySemesterIdAndCourseId(semesterId, courseId);
    }

    @Override
    public ClassInfoVo getClassInfoVo(@NonNull final Integer classId) {
        return classMapper.selectByClassId(classId);
    }

    @Override
    public boolean isMyClass(Integer headId, Integer classId) {
        return classMapper.countByClassIdAndHeadId(classId, headId) > 0;
    }
}
