package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.user.*;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.user.SuccessfulLoginVo;

public interface StaffBusiness {

    SuccessfulLoginVo login(UserLoginBo bo);

    void firstTimeChangePassword(FirstTimeChangePasswordBo bo);

    void changePassword(Integer userId, ChangePasswordBo bo);

    void updateStaffInfo(Integer userId, UpdateUserInfo userInfo);

    void logout(String token, UserInfo userInfo);

    void createStaff(String creator, CreateStaffBo bo);
}
