package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Staff;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.enums.UserGroup;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.user.*;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserLoginDto;
import com.abstractionizer.studentInformationSystem6.models.vo.user.SuccessfulLoginVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentBusiness;
import com.abstractionizer.studentInformationSystem6.sis.businesses.BaseBusinesses.BaseUserBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.LoginService;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentService;
import com.abstractionizer.studentInformationSystem6.utils.MD5Util;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.abstractionizer.studentInformationSystem6.constants.RedisConstant.*;

@Slf4j
@Service
public class StudentBusinessImpl extends BaseUserBusiness<Student> implements StudentBusiness {

    private final StudentService studentService;

    @Autowired
    public StudentBusinessImpl(StudentService studentService, LoginService loginService){
        super(loginService);
        this.studentService = studentService;
    }

    @Override
    public void create(@NonNull final String creator, @NonNull final CreateStudentBo bo) {
        Student student = new Student(
                MD5Util.md5(this.generateDefaultPassword(bo.getBirthday())),
                bo.getUsername(),
                bo.getBirthday(),
                bo.getEmail(),
                bo.getPhone(),
                bo.getAddress(),
                creator
        );
        studentService.create(student);
    }

    @Override
    public SuccessfulLoginVo login(@NonNull final UserLoginBo bo) {
        Student student = studentService.getStudent(bo.getUserId()).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        final UserLoginDto dto = this.authenticate(bo.getPassword(), student, studentService::freezeAccount, getStudentLoggedInKey(student.getId()), getStudentLoginFailureCountKey(student.getId()));

        this.loginService.setUserLoginToken(dto.getToken(), dto.getUserInfo().setUserGroup(UserGroup.STUDENT));

        return new SuccessfulLoginVo(dto.getToken());
    }

    @Override
    public void firstTimeChangePassword(@NonNull final FirstTimeChangePasswordBo bo) {
        Student student = studentService.getStudent(bo.getUserId()).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        this.firstTimeChangePassword(student, bo.getOldPassword(), bo.getNewPassword(), studentService::firstTimeChangePassword);
    }

    @Override
    public void changePassword(@NonNull final Integer userId, @NonNull final ChangePasswordBo bo) {
        Student student = studentService.getStudent(userId).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_CREDENTIALS));

        this.changePassword(student, bo.getOldPassword(), bo.getNewPassword(), studentService::changePassword);
    }

    @Override
    public void updateUserInfo(@NonNull final Integer userId, @NonNull final UpdateUserInfo userInfo) {
        if(!studentService.isStudentIdExists(userId)){
            throw new CustomExceptions(ErrorCode.USER_NON_EXISTS);
        }

        Student staff = new Student(userId, userInfo.getEmail(), userInfo.getPhone(), userInfo.getAddress());
        this.updateUserInfo(staff, studentService::updateStudentInfo);
    }

    @Override
    public void logout(@NonNull final String token, @NonNull final UserInfo userInfo) {
        super.logout(token, getStudentLoggedInKey(userInfo.getId()));
    }
}
