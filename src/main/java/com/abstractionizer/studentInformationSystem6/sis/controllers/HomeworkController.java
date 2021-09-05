package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.homework.CreateHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.SubmitHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.homework.HomeworkDto2;
import com.abstractionizer.studentInformationSystem6.models.vo.homework.HomeworkVo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.HomeworkBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

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

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/{id}")
    public SuccessResponse<List<HomeworkVo>> getHomeworks(@RequestAttribute("Student") UserInfo userInfo,
                                                          @PathVariable("id") Integer classId){
        return new SuccessResponse<>(homeworkBusiness.getValidHomeworks(userInfo.getId(), classId));
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/submit")
    public SuccessResponse<BigDecimal> submitHomework(@RequestAttribute("Student") UserInfo userInfo,
                                                      @RequestBody @Valid SubmitHomeworkBo bo){
        return new SuccessResponse<>(homeworkBusiness.submitHomework(userInfo.getId(), bo));
    }
}
