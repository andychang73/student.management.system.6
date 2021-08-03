package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.semester.CreateSemesterBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.SemesterBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/semester")
public class SemesterController {

    private final SemesterBusiness semesterBusiness;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SuccessResponse create(@RequestAttribute("Staff")UserInfo userInfo,
                                  @RequestBody @Valid CreateSemesterBo bo){
        semesterBusiness.create(userInfo.getUsername(), bo);
        return new SuccessResponse();
    }
}
