package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.classes.CreateClassBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.ClassBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/class")
public class ClassController {

    private final ClassBusiness classBusiness;

    @PreAuthorize("hasRole('ROLE_HEAD')")
    @PostMapping
    public SuccessResponse create(@RequestAttribute("Staff") UserInfo userInfo,
                                  @RequestBody @Valid CreateClassBo bo){
        classBusiness.create(userInfo, bo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasRole('ROLE_HEAD')")
    @GetMapping("/week")
    public SuccessResponse<ClassesOfTheWeekVo> getClassesOfTheWeek(@RequestAttribute("Staff") UserInfo userInfo,
                                                                   @RequestParam("courseId") Integer courseId){
        return new SuccessResponse<>(classBusiness.getClassesOfTheWeek(userInfo.getId(), courseId));
    }

    @PreAuthorize("hasRole('ROLE_HEAD')")
    @GetMapping("/{id}")
    public SuccessResponse<ClassInfoVo> getClassInfoVo(@RequestAttribute("Staff") UserInfo userInfo,
                                                       @PathVariable("id") Integer classId){
        return new SuccessResponse<>(classBusiness.getClassInfoVo(userInfo.getId(), classId));
    }

}
