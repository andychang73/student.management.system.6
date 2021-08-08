package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.user.*;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.user.SuccessfulLoginVo;
import com.abstractionizer.studentInformationSystem6.models.vo.user.teacher.TeacherVo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StaffBusiness;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/staff")
public class StaffController {

    private final StaffBusiness staffBusiness;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public SuccessResponse createStaff(@RequestAttribute("Staff") UserInfo userInfo,
                                       @RequestBody @Valid CreateStaffBo bo){
        staffBusiness.createStaff(userInfo.getUsername(), bo);
        return new SuccessResponse();
    }

    @PostMapping("/login")
    public SuccessResponse<SuccessfulLoginVo> login(@RequestBody @Valid UserLoginBo bo){
        return new SuccessResponse<>(staffBusiness.login(bo));
    }

    @PutMapping("/firstTimeChangePassword")
    public SuccessResponse firstTimeChangePassword(@RequestBody @Valid FirstTimeChangePasswordBo bo){
        staffBusiness.firstTimeChangePassword(bo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HEAD', 'ROLE_TEACHER')")
    @PutMapping("/password")
    public SuccessResponse changePassword(@RequestAttribute("Staff") UserInfo userInfo,
                                          @RequestBody @Valid ChangePasswordBo bo){
        staffBusiness.changePassword(userInfo.getId(), bo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HEAD', 'ROLE_TEACHER')")
    @PutMapping
    public SuccessResponse updateStaffIno(@RequestAttribute("Staff") UserInfo userInfo,
                                          @RequestBody @Valid UpdateUserInfo updateUserInfo){
        staffBusiness.updateStaffInfo(userInfo.getId(), updateUserInfo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HEAD', 'ROLE_TEACHER')")
    @GetMapping("/logout")
    public SuccessResponse logout(@RequestAttribute("Staff") UserInfo userInfo,
                                  @RequestHeader("token") String token){
        staffBusiness.logout(token, userInfo);
        return new SuccessResponse();
    }

    @PreAuthorize("hasRole('ROLE_HEAD')")
    @GetMapping("/myTeachers")
    public SuccessResponse<List<TeacherVo>> getMyTeachers(@RequestAttribute("Staff") UserInfo userInfo){
        return new SuccessResponse<>(staffBusiness.getMyTeachers(userInfo.getId()));
    }
}
