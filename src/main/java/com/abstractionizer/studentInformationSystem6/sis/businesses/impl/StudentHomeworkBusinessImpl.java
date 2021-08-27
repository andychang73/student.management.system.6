package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentAverageGradesVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentWeeklyHomeworkGradeVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentHomeworkBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.ClassService;
import com.abstractionizer.studentInformationSystem6.sis.services.DateConfigService;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentHomeworkService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentHomeworkBusinessImpl implements StudentHomeworkBusiness {

    private final StudentHomeworkService studentHomeworkService;
    private final DateConfigService dateConfigService;
    private final ClassService classService;

    @Override
    public List<StudentWeeklyHomeworkGradeVo> getAllHomeworkGrades(@NonNull final Integer staffId, @NonNull final Integer classId) {
        if(!classService.isClassValid(classId,staffId, dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.CLASS_INVALID);
        }

        return studentHomeworkService.getAllHomeWorkGrades(classId, dateConfigService.getDate());
    }

    @Override
    public List<StudentAverageGradesVo> getStudentAverageGrades(@NonNull final Integer staffId, @NonNull final Integer classId) {
        if(!classService.isClassValid(classId, staffId, dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.CLASS_INVALID);
        }

        return classService.getStudentAverageGrades(classId, classService.getNumberOfHomework(classId));
    }
}
