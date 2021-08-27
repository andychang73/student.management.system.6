package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.constants.ProjectConstant;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.studentCourse.GradingBo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentCourseBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.ClassService;
import com.abstractionizer.studentInformationSystem6.sis.services.DateConfigService;
import com.abstractionizer.studentInformationSystem6.sis.services.SemesterService;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentCourseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class StudentCourseBusinessImpl implements StudentCourseBusiness {

    private final StudentCourseService studentCourseService;
    private final SemesterService semesterService;
    private final DateConfigService dateConfigService;
    private final ClassService classService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void grading(@NonNull final Integer staffId, @NonNull final GradingBo bo) {
        if(!semesterService.isSemesterEnded(dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.SEMESTER_NOT_ENDED, "Can't give final grade until semester ends");
        }

        StudentCourse studentCourse = classService.isStudentInThisClass(bo.getClassId(), semesterService.getCurrentSemester().getId(), bo.getStudentId())
                .orElseThrow(()-> new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS));

        if(Objects.nonNull(studentCourse.getFinalGrade())){
            throw new CustomExceptions(ErrorCode.Course_Graded);
        }

        Float pass = 60F;
        studentCourse.setFinalGrade(bo.getGrade()).setStatus(bo.getGrade() >= pass ? ProjectConstant.StudentCourseStatus.PASSED : ProjectConstant.StudentCourseStatus.FAILED);
        studentCourseService.updateFinalGrade(studentCourse);

        if(bo.getGrade() < pass){
            return;
        }

        studentCourseService.updateNumOfCompletedPreCourse(bo.getStudentId(), bo.getClassId());
        studentCourseService.updateCourseStatusIfPreCoursesCompleted(bo.getStudentId());
    }
}
