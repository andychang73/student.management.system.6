package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.assessment.CreateAssessmentBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.assessment.AssessmentVo;

public interface AssessmentBusiness {

    void assess(UserInfo headInfo, CreateAssessmentBo bo);

    AssessmentVo getTeacherAssessmentResults(Integer headId, Integer teacherId);
}
