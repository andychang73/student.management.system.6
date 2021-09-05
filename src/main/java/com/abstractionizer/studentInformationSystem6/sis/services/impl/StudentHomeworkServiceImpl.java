package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentHomework;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StudentHomeworkMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentWeeklyHomeworkGradeVo;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentHomeworkService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentHomeworkServiceImpl implements StudentHomeworkService {

    private final StudentHomeworkMapper studentHomeworkMapper;

    @Override
    public List<StudentWeeklyHomeworkGradeVo> getAllHomeWorkGrades(@NonNull final Integer classId, @NonNull final Date now) {
        return studentHomeworkMapper.selectByClassIdAndDeadLine(classId, now);
    }

    @Override
    public boolean hasHomeworkBeenSubmitted(@NonNull final Integer homeworkId, @NonNull final Integer studentId) {
        return studentHomeworkMapper.countByHomeworkIdAndStudentId(homeworkId, studentId) > 0;
    }

    @Override
    public void create(@NonNull final StudentHomework studentHomework) {
        if(studentHomeworkMapper.insert(studentHomework) != 1){
            log.error("Failed to insert student homework");
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }
}
