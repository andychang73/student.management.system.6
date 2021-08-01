package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.college.CreateCollegeBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.CollegeBusiness;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/college")
public class CollegeController {

    private final CollegeBusiness collegeBusiness;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SuccessResponse create(@RequestAttribute("Staff")UserInfo userInfo,
                                  @RequestBody @Valid CreateCollegeBo bo){
        collegeBusiness.create(userInfo.getUsername(), bo);
        return new SuccessResponse();
    }
}
