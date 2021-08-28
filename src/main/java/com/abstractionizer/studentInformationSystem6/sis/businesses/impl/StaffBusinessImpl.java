package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Staff;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.UserRole;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.enums.UserGroup;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.user.*;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserLoginDto;
import com.abstractionizer.studentInformationSystem6.models.vo.user.SuccessfulLoginVo;
import com.abstractionizer.studentInformationSystem6.models.vo.user.teacher.TeacherVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StaffBusiness;
import com.abstractionizer.studentInformationSystem6.sis.businesses.BaseBusinesses.BaseUserBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.LoginService;
import com.abstractionizer.studentInformationSystem6.sis.services.RoleService;
import com.abstractionizer.studentInformationSystem6.sis.services.StaffService;
import com.abstractionizer.studentInformationSystem6.sis.services.UserRoleService;
import com.abstractionizer.studentInformationSystem6.utils.MD5Util;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.abstractionizer.studentInformationSystem6.constants.RedisConstant.*;

@Slf4j
@Service
public class StaffBusinessImpl extends BaseUserBusiness<Staff> implements StaffBusiness {

    private final StaffService staffService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Autowired
    public StaffBusinessImpl(LoginService loginService, StaffService staffService, RoleService roleService, UserRoleService userRoleService) {
        super(loginService);
        this.staffService = staffService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @Override
    public SuccessfulLoginVo login(@NonNull final UserLoginBo bo) {
        Staff staff = staffService.getStaff(bo.getUserId()).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        final UserLoginDto dto = this.authenticate(bo.getPassword(), staff, staffService::freezeAccount, getStaffLoggedInKey(staff.getId()), getStaffLoginFailureCountKey(staff.getId()));

        this.loginService.setUserLoginToken(dto.getToken(), dto.getUserInfo().setUserGroup(UserGroup.STAFF));

        return new SuccessfulLoginVo(dto.getToken());
    }

    @Override
    public List<TeacherVo> getMyTeachers(@NonNull final Integer headId) {
        return staffService.selectByReportTo(headId);
    }

    @Override
    public void firstTimeChangePassword(@NonNull FirstTimeChangePasswordBo bo) {
        Staff staff = staffService.getStaff(bo.getUserId()).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        this.firstTimeChangePassword(staff, bo.getOldPassword(), bo.getNewPassword(), staffService::firstTimeChangePassword);
    }

    @Override
    public void changePassword(@NonNull final Integer userId, @NonNull ChangePasswordBo bo) {
        Staff staff = staffService.getStaff(userId).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        this.changePassword(staff, bo.getOldPassword(), bo.getNewPassword(), staffService::changePassword);
    }

    @Override
    public void updateUserInfo(@NonNull final Integer userId, @NonNull final UpdateUserInfo userInfo) {
        if(!staffService.isStaffExists(userId)){
            throw new CustomExceptions(ErrorCode.USER_NON_EXISTS);
        }

        Staff staff = new Staff(userId, userInfo.getEmail(), userInfo.getPhone(), userInfo.getAddress());
        this.updateUserInfo(staff, staffService::updateStaffInfo);
    }

    @Override
    public void logout(@NonNull final String token, @NonNull final UserInfo userInfo) {
        super.logout(token, getStaffLoggedInKey(userInfo.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createStaff(@NonNull final String creator, @NonNull final CreateStaffBo bo) {
        if(!roleService.areRoleIdsExist(bo.getRoleIds())){
            throw new CustomExceptions(ErrorCode.ROLE_NOT_EXISTS);
        }
        if(Objects.nonNull(bo.getReportTo())){
            if(!staffService.isStaffExists(bo.getReportTo())){
                throw new CustomExceptions(ErrorCode.USER_NON_EXISTS);
            }
        }

        Staff staff = staffService.create(generateStaff(creator, bo));

        userRoleService.create(generateUserRoles(staff.getId(), bo.getRoleIds()));
    }

    private Staff generateStaff(@NonNull final String creator, @NonNull final CreateStaffBo bo){
        return new Staff(MD5Util.md5(this.generateDefaultPassword(bo.getBirthday())),
                bo.getUsername(),
                bo.getBirthday(),
                bo.getEmail(),
                bo.getPhone(),
                bo.getAddress(),
                bo.getReportTo() != null ? bo.getReportTo() : null,
                creator);
    }

    private Set<UserRole> generateUserRoles(@NonNull final Integer staffId, @NonNull final Set<Integer> roleIds){
        return roleIds.stream().map(r -> new UserRole().setUserId(staffId).setRoleId(r)).collect(Collectors.toSet());
    }
}
