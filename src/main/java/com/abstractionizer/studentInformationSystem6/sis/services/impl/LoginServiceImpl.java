package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.sis.services.LoginService;
import com.abstractionizer.studentInformationSystem6.utils.MD5Util;
import com.abstractionizer.studentInformationSystem6.utils.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.abstractionizer.studentInformationSystem6.constants.RedisConstant.*;

@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final RedisUtil redisUtil;
    private final Long loginDuration = 240L;

    @Override
    public boolean authenticate(@NonNull final String enteredPassword, @NonNull final String password) {
        return MD5Util.md5(enteredPassword).equals(password);
    }

    @Override
    public Long loginFailedCount(@NonNull final String userLoginFailedKey) {
        Long interval = 1L;
        if(!redisUtil.isKeyExists(userLoginFailedKey)){
            long loginFailedDuration = 30L;
            redisUtil.set(userLoginFailedKey, interval, loginFailedDuration, TimeUnit.MINUTES);
            return interval;
        }
        return redisUtil.increment(userLoginFailedKey, interval);
    }

    @Override
    public void deleteLoginFailedCount(@NonNull String userLoginFailedKey) {
        redisUtil.deleteKey(userLoginFailedKey);
    }

    @Override
    public boolean isUserLoggedIn(@NonNull final String key) {
        return redisUtil.isKeyExists(key);
    }

    @Override
    public Optional<String> generateToken() {
        String token;
        int count = 0;
        while(true){
            token = UUID.randomUUID().toString();
            if(!redisUtil.isKeyExists(getUserLoginTokenKey(token))){
                break;
            }
            if(++count >= 3){
                token = null;
                break;
            }
        }
        return Optional.ofNullable(token);
    }

    @Override
    public void setUserLoginToken(@NonNull final String token, @NonNull final UserInfo userInfo) {
        redisUtil.set(getUserLoginTokenKey(token), userInfo, loginDuration, TimeUnit.DAYS);
    }

    @Override
    public void setUserLoginId(@NonNull final String key) {
        redisUtil.set(key, key, loginDuration, TimeUnit.DAYS);
    }

    @Override
    public Optional<UserInfo> getUserInfoByToken(String token) {
        return Optional.ofNullable(redisUtil.get(getUserLoginTokenKey(token), UserInfo.class));
    }

    @Override
    public void deleteUserInfoByToken(String token) {
        redisUtil.deleteKey(getUserLoginTokenKey(token));
    }

    @Override
    public void deleteUserLoginId(String key) {
        redisUtil.deleteKey(key);
    }
}
