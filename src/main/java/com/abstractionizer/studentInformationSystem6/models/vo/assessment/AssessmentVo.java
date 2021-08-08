package com.abstractionizer.studentInformationSystem6.models.vo.assessment;

import com.abstractionizer.studentInformationSystem6.models.dto.assessment.AssessmentDto;
import lombok.Data;

import java.util.List;

@Data
public class AssessmentVo {
    private Integer staffId;
    private String teacherName;
    private List<AssessmentDto> assessments;
}
