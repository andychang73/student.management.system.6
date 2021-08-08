package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Assessment;
import com.abstractionizer.studentInformationSystem6.models.vo.assessment.AssessmentVo;

public interface AssessmentService {

    void create(Assessment assessment);

    AssessmentVo getAssessments(Integer staffId);

    boolean hasBeenAssessed(Integer semesterId, Integer staffId);
}
