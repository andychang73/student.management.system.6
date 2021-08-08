package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.studentMajor.CreateStudentMajorBo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentMajorBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/studentMajor")
@AllArgsConstructor
public class StudentMajorController {

    private final StudentMajorBusiness studentMajorBusiness;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SuccessResponse create(@RequestBody @Valid CreateStudentMajorBo bo){
        studentMajorBusiness.create(bo);
        return new SuccessResponse();
    }
}
