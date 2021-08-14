package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.ClassMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import com.abstractionizer.studentInformationSystem6.sis.services.ClassService;
import com.abstractionizer.studentInformationSystem6.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

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
    public boolean isMyClass(@NonNull final Integer headId, @NonNull final Integer classId) {
        return classMapper.countByClassIdAndOrHeadId(classId, headId) > 0;
    }

    @Override
    public boolean isClassMine(Integer id, Integer staffId) {
        return classMapper.countByClassIdAndStaffId(id, staffId) > 0;
    }

    @Override
    public boolean isClassExists(@NonNull final Integer classId) {
        return classMapper.countByClassIdAndOrHeadId(classId, null) > 0;
    }

    @Override
    public List<ClassVo> getMyClassesOfThisSemester(@NonNull final Integer staffId, @NonNull final Integer semesterId) {
        return classMapper.selectByStaffIdAndSemesterId(staffId, semesterId);
    }
}
