package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Assessment;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.enums.UserRole;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.assessment.CreateAssessmentBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.assessment.AssessmentVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.AssessmentBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AssessmentBusinessImpl implements AssessmentBusiness {

    private final AssessmentService assessmentService;
    private final DateConfigService dateConfigService;
    private final SemesterService semesterService;
    private final UserRoleService userRoleService;
    private final StaffService staffService;

    @Override
    public void assess(@NonNull final UserInfo head, @NonNull final CreateAssessmentBo bo) {
        if(!userRoleService.isUserMatchingRole(bo.getStaffId(), UserRole.TEACHER.getRoleId())){
            throw new CustomExceptions(ErrorCode.USER_ROLE_UNMATCHED, "This particular staff is not a teacher");
        }
        if(!staffService.isTeacherWorkingForThisHeadOfCourse(bo.getStaffId(), head.getId())){
            throw new CustomExceptions(ErrorCode.TEACHER_WRONG_COURSE, "This teacher does not report to this head");
        }
        if(!semesterService.isSemesterEnded(dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.ASSESSMENT_FORBIDDEN);
        }

        Semester semester = semesterService.getCurrentSemester();

        if(assessmentService.hasBeenAssessed(semester.getId(), bo.getStaffId())){
            throw new CustomExceptions(ErrorCode.ASSESSED);
        }

        Assessment assessment = new Assessment()
                .setSemesterId(semester.getId())
                .setStaffId(bo.getStaffId())
                .setResult(bo.getResult())
                .setAssessedBy(head.getUsername());

        assessmentService.create(assessment);
    }

    @Override
    public AssessmentVo getTeacherAssessmentResults(@NonNull final Integer headId, @NonNull final Integer teacherId) {
        if(!userRoleService.isUserMatchingRole(teacherId, UserRole.TEACHER.getRoleId())){
            throw new CustomExceptions(ErrorCode.USER_ROLE_UNMATCHED, "This particular staff is not a teacher");
        }
        if(!staffService.isTeacherWorkingForThisHeadOfCourse(teacherId, headId)){
            throw new CustomExceptions(ErrorCode.TEACHER_WRONG_COURSE, "This teacher does not report to this head");
        }
        return assessmentService.getAssessments(teacherId);
    }

    @Override
    public AssessmentVo getMyAssessment(@NonNull final Integer teacherId) {
        return assessmentService.getAssessments(teacherId);
    }
}
