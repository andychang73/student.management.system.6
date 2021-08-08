package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.course.CreateCourseBo;
import com.abstractionizer.studentInformationSystem6.models.vo.course.CourseVo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.CourseBusiness;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseBusiness courseBusiness;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SuccessResponse create(@RequestAttribute("Staff")UserInfo userInfo,
                                  @RequestBody @Valid CreateCourseBo bo){
        courseBusiness.create(userInfo.getUsername(), bo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public SuccessResponse<List<CourseVo>> getAllCourses(){
        return new SuccessResponse<>(courseBusiness.getAllCourses());
    }

    @PreAuthorize("hasRole('ROLE_HEAD')")
    @GetMapping("/myCourses")
    public SuccessResponse<List<CourseVo>> getMyCourses(@RequestAttribute("Staff") UserInfo userInfo){
        return new SuccessResponse<>(courseBusiness.getMyCourses(userInfo.getId()));
    }
}
