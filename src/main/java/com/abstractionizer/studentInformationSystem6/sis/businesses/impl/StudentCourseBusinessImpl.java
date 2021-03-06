package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.constants.ProjectConstant;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentClass;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.studentCourse.GradingBo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentCourseVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentIdAndCourseInfoVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentCourseBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class StudentCourseBusinessImpl implements StudentCourseBusiness {

    private final StudentCourseService studentCourseService;
    private final StudentClassService studentClassService;
    private final SemesterService semesterService;
    private final StudentService studentService;
    private final DateConfigService dateConfigService;
    private final ClassService classService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void grading(@NonNull final Integer staffId, @NonNull final GradingBo bo) {
        if(!semesterService.isSemesterEnded(dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.SEMESTER_NOT_ENDED, "Can't give final grade until semester ends");
        }

        Integer semesterId = semesterService.getCurrentSemester().getId();

        StudentCourse studentCourse = classService.isStudentInThisClass(bo.getClassId(), semesterId, bo.getStudentId())
                .orElseThrow(()-> new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS));

        if(Objects.nonNull(studentCourse.getFinalGrade())){
            throw new CustomExceptions(ErrorCode.Course_Graded);
        }

        Float pass = 60F;
        studentCourse.setFinalGrade(bo.getGrade())
                .setFinalAttendance(studentClassService.getAttendance(bo.getStudentId(), bo.getClassId(), semesterId))
                .setStatus(bo.getGrade() >= pass ? ProjectConstant.StudentCourseStatus.PASSED : ProjectConstant.StudentCourseStatus.FAILED);
        studentCourseService.updateFinalGradeAndFinalAttendance(studentCourse);

        if(bo.getGrade() < pass){
            return;
        }

        studentCourseService.updateNumOfCompletedPreCourse(bo.getStudentId(), bo.getClassId());
        studentCourseService.updateCourseStatusIfPreCoursesCompleted(bo.getStudentId());
    }

    @Override
    public List<StudentCourseVo> getStudentCourseStatus(@NonNull final Integer studentId) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }
        return studentCourseService.getStudentCourseStatus(studentId);
    }

    @Override
    public List<StudentIdAndCourseInfoVo> getAvailableCourses(@NonNull final Integer studentId) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }

        Semester semester = semesterService.getCurrentSemester();
        if(semester.getEndDate().before(dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.INVALID_SEMESTER, "New semester has not yet been created, please contact admin");
        }
        if(!classService.isAllowedToEnroll(dateConfigService.getDate(), semester.getStartDate())){
            throw new CustomExceptions(ErrorCode.CLASS_ENROLLMENT_NOT_ALLOWED);
        }

        return studentCourseService.getAvailableCourses(studentId);
    }
}
