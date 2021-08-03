package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.user.CreateStudentBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentBusiness;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentBusiness studentBusiness;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SuccessResponse create(@RequestAttribute("Staff")UserInfo userInfo,
                                  @RequestBody @Valid CreateStudentBo bo){
        studentBusiness.create(userInfo.getUsername(), bo);
        return new SuccessResponse();
    }
}
