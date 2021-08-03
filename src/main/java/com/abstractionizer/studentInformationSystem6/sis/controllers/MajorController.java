package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.major.CreateMajorBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.major.MajorVo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.MajorBusiness;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/major")
public class MajorController {

    private final MajorBusiness majorBusiness;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SuccessResponse create(@RequestAttribute("Staff") UserInfo userInfo,
                                  @RequestBody @Valid CreateMajorBo bo){
        majorBusiness.create(userInfo.getUsername(), bo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public SuccessResponse<List<MajorVo>> getAllMajors(){
        return new SuccessResponse<>(majorBusiness.getAllMajors());
    }
}
