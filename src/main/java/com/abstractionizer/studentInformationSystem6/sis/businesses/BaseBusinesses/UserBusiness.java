package com.abstractionizer.studentInformationSystem6.sis.businesses.BaseBusinesses;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.User;
import com.abstractionizer.studentInformationSystem6.enums.AccountStatus;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserLoginDto;
import com.abstractionizer.studentInformationSystem6.sis.services.LoginService;
import com.abstractionizer.studentInformationSystem6.utils.MD5Util;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import static com.abstractionizer.studentInformationSystem6.constants.RedisConstant.*;

@Slf4j
@AllArgsConstructor
public class UserBusiness<T extends User> {

    protected final LoginService loginService;

    protected UserLoginDto authenticate(String enteredPassword, T user, Consumer<Integer> freezeAccount, String userLoggedInKey){
        if(!loginService.authenticate(enteredPassword.trim(), user.getPassword())){
            long loginFailedCount = loginService.loginFailedCount(getStaffLoginFailureCountKey(user.getId()));
            long loginAttemptLimit = 3;
            if(loginFailedCount >= loginAttemptLimit){
                freezeAccount.accept(user.getId());
                throw new CustomExceptions(ErrorCode.ACCOUNT_FROZEN);
            }
            throw new CustomExceptions(ErrorCode.INVALID_CREDENTIALS, "You have " + (loginAttemptLimit - loginFailedCount) + " attempt left");
        }

        if(user.getFirstTimeLogin().equals(AccountStatus.FIRST_LOGIN.getStatus())){
            throw new CustomExceptions(ErrorCode.USER_FIRST_LOGIN);
        }

        if(user.getStatus().equals(AccountStatus.FROZEN.getStatus())){
            throw new CustomExceptions(ErrorCode.ACCOUNT_FROZEN);
        }

        if(loginService.isUserLoggedIn(getStaffLoggedInKey(user.getId()))){
            throw new CustomExceptions(ErrorCode.USER_LOGGED_IN);
        }

        String token = loginService.generateToken().orElseThrow(() -> new RuntimeException("Failed to generate token"));

        UserInfo userInfo = new UserInfo();

        BeanUtils.copyProperties(user, userInfo);
        loginService.setUserLoginId(userLoggedInKey);

        return new UserLoginDto(token, userInfo);
    }

    protected void firstTimeChangePassword(T user, String oldPassword, String newPassword, BiConsumer<Integer, String> firstTimeChangePassword){
        if(!loginService.authenticate(oldPassword.trim(), user.getPassword())){
            throw new CustomExceptions(ErrorCode.INVALID_CREDENTIALS);
        }
        if(user.getFirstTimeLogin().equals(AccountStatus.NOT_FIRST_LOGIN.getStatus())){
            throw new CustomExceptions(ErrorCode.USER_NOT_FIRST_LOGIN);
        }

        firstTimeChangePassword.accept(user.getId(), MD5Util.md5(newPassword.trim()));
    }

    protected void changePassword(T user, String oldPassword, String newPassword, BiConsumer<Integer, String> changePassword){
        if(!loginService.authenticate(oldPassword, user.getPassword())){
            throw new CustomExceptions(ErrorCode.INVALID_CREDENTIALS);
        }
        changePassword.accept(user.getId(), MD5Util.md5(newPassword.trim()));
    }

    protected void updateUserInfo(T user, Consumer<T> updateUserInfo){
        updateUserInfo.accept(user);
    }

    protected void logout(String token, String key){
        loginService.deleteUserInfoByToken(token);
        loginService.deleteUserLoginId(key);
    }

    protected String generateDefaultPassword(@NonNull final Date birthday){
        StringBuilder sb = new StringBuilder();
        List.of(new SimpleDateFormat("yyyy-MM-dd").format(birthday).split("-")).forEach(sb::append);
        return sb.toString();
    }
}
