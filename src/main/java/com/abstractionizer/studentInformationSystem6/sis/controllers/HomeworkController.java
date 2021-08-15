package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.homework.CreateHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.HomeworkBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/homework")
public class HomeworkController {

    private final HomeworkBusiness homeworkBusiness;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping
    public SuccessResponse create(@RequestAttribute("Staff")UserInfo userInfo,
                                  @RequestBody @Valid CreateHomeworkBo bo){
        homeworkBusiness.create(userInfo.getId(), bo);
        return new SuccessResponse();
    }
}
