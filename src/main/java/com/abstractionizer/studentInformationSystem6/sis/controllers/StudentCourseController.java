package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.studentCourse.GradingBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentCourseBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/studentCourse")
public class StudentCourseController {

    private final StudentCourseBusiness studentCourseBusiness;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping
    public SuccessResponse grading(@RequestAttribute("Staff")UserInfo userInfo,
                                   @RequestBody @Valid GradingBo bo){
        studentCourseBusiness.grading(userInfo.getId(), bo);
        return new SuccessResponse();
    }
}
