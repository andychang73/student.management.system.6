package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentClassBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/studentClass")
public class StudentClassController {

    private final StudentClassBusiness studentClassBusiness;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/{id}")
    public SuccessResponse<StudentsOfTheClass> getStudentsOfTheClass(@RequestAttribute("Staff")UserInfo userInfo,
                                                                     @PathVariable("id") Integer classId){
        return new SuccessResponse<>(studentClassBusiness.getStudentsOfTheClass(userInfo.getId(), classId));
    }
}
