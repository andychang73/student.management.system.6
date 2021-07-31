package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import java.util.Optional;


public interface LoginService {

    boolean authenticate(String enteredPassword, String password);

    Long loginFailedCount(String key);

    boolean isUserLoggedIn(String key);

    Optional<String> generateToken();

    void setUserLoginToken(String token, UserInfo userInfo);

    void setUserLoginId(String key);

    Optional<UserInfo> getUserInfoByToken(String token);

    void deleteUserInfoByToken(String token);

    void deleteUserLoginId(String key);

}
