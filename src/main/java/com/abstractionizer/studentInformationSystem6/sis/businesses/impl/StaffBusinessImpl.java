package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Staff;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.enums.UserGroup;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.user.ChangePasswordBo;
import com.abstractionizer.studentInformationSystem6.models.bo.user.FirstTimeChangePasswordBo;
import com.abstractionizer.studentInformationSystem6.models.bo.user.UpdateUserInfo;
import com.abstractionizer.studentInformationSystem6.models.bo.user.UserLoginBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserLoginDto;
import com.abstractionizer.studentInformationSystem6.models.vo.user.SuccessfulLoginVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StaffBusiness;
import com.abstractionizer.studentInformationSystem6.sis.businesses.UserBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.LoginService;
import com.abstractionizer.studentInformationSystem6.sis.services.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.abstractionizer.studentInformationSystem6.constants.RedisConstant.*;

@Slf4j
@Service
public class StaffBusinessImpl extends UserBusiness<Staff> implements StaffBusiness {

    private final StaffService staffService;

    @Autowired
    public StaffBusinessImpl(LoginService loginService, StaffService staffService) {
        super(loginService);
        this.staffService = staffService;
    }

    @Override
    public SuccessfulLoginVo login(UserLoginBo bo) {
        Staff staff = staffService.getStaff(bo.getUserId()).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        final UserLoginDto dto = this.validateCredentials(bo.getPassword(), staff, staffService::freezeAccount, getStaffLoggedInKey(staff.getId()));

        this.loginService.setUserLoginToken(dto.getToken(), dto.getUserInfo().setUserGroup(UserGroup.STAFF));

        return new SuccessfulLoginVo(dto.getToken());
    }

    @Override
    public void firstTimeChangePassword(FirstTimeChangePasswordBo bo) {
        Staff staff = staffService.getStaff(bo.getUserId()).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        this.firstTimeChangePassword(staff, bo.getOldPassword(), bo.getNewPassword(), staffService::firstTimeChangePassword);
    }

    @Override
    public void changePassword(Integer userId, ChangePasswordBo bo) {
        Staff staff = staffService.getStaff(userId).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        this.changePassword(staff, bo.getOldPassword(), bo.getNewPassword(), staffService::changePassword);
    }

    @Override
    public void updateStaffInfo(Integer userId, UpdateUserInfo userInfo) {
        if(!staffService.isStaffExists(userId)){
            throw new CustomExceptions(ErrorCode.USER_NON_EXISTS);
        }

        Staff staff = new Staff(userId, userInfo.getEmail(), userInfo.getPhone(), userInfo.getAddress());
        this.updateUserInfo(staff, staffService::updateStaffInfo);
    }

    @Override
    public void logout(String token, UserInfo userInfo) {
        super.logout(token, getStaffLoggedInKey(userInfo.getId()));
    }

}
