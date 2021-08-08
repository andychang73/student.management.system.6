package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.assessment.CreateAssessmentBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.assessment.AssessmentVo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.AssessmentBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/assessment")
public class AssessmentController {

    private final AssessmentBusiness assessmentBusiness;

    @PreAuthorize("hasRole('ROLE_HEAD')")
    @GetMapping("/{id}")
    public SuccessResponse<AssessmentVo> getTeacherAssessments(@RequestAttribute("Staff")UserInfo userInfo,
                                                               @PathVariable("id") Integer id){
        return new SuccessResponse<>(assessmentBusiness.getTeacherAssessmentResults(userInfo.getId(), id));
    }

    @PreAuthorize("hasRole('ROLE_HEAD')")
    @PostMapping
    public SuccessResponse assess(@RequestAttribute("Staff") UserInfo userInfo,
                                  @RequestBody @Valid CreateAssessmentBo bo){
        assessmentBusiness.assess(userInfo, bo);
        return new SuccessResponse();
    }
}
