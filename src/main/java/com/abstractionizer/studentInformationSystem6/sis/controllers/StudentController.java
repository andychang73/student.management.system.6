package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.user.*;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.user.SuccessfulLoginVo;
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

    @PostMapping("/login")
    public SuccessResponse<SuccessfulLoginVo> login(@RequestBody @Valid UserLoginBo bo){
        return new SuccessResponse<>(studentBusiness.login(bo));
    }

    @PutMapping("/firstTimeChangePassword")
    public SuccessResponse firstTimeChangePassword(@RequestBody @Valid FirstTimeChangePasswordBo bo){
        studentBusiness.firstTimeChangePassword(bo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PutMapping("/changePassword")
    public SuccessResponse changePassword(@RequestAttribute("Student") UserInfo userInfo,
                                          @RequestBody @Valid ChangePasswordBo bo){
        studentBusiness.changePassword(userInfo.getId(), bo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PutMapping
    public SuccessResponse updateStudentInfo(@RequestAttribute("Student") UserInfo userInfo,
                                             @RequestBody @Valid UpdateUserInfo updateUserInfo){
        studentBusiness.updateUserInfo(userInfo.getId(), updateUserInfo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/logout")
    public SuccessResponse logout(@RequestAttribute("Student") UserInfo userInfo,
                                  @RequestHeader("token") String token){
        studentBusiness.logout(token, userInfo);
        return new SuccessResponse();
    }
}
