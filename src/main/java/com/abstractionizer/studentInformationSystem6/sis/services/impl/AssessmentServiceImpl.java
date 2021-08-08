package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Assessment;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.AssessmentMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.vo.assessment.AssessmentVo;
import com.abstractionizer.studentInformationSystem6.sis.services.AssessmentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentMapper assessmentMapper;

    @Override
    public void create(@NonNull final Assessment assessment) {
        if(assessmentMapper.insert(assessment) != 1){
            log.error("Failed to create assessment: {}", assessment);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }

    @Override
    public AssessmentVo getAssessments(@NonNull final Integer staffId) {
        return assessmentMapper.selectByStaffId(staffId);
    }

    @Override
    public boolean hasBeenAssessed(@NonNull final Integer semesterId, @NonNull final Integer staffId) {
        return assessmentMapper.countBySemesterIdAndStaffId(semesterId, staffId) > 0;
    }
}
