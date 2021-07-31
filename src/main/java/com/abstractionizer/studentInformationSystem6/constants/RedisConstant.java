package com.abstractionizer.studentInformationSystem6.constants;

public class RedisConstant {

    public static final String STAFF_LOGIN_ID = "SMS:STAFF:LOGIN:ID:%s";
    public static final String STAFF_LOGIN_FAILURE = "SMS:STAFF:LOGIN:FAILED:%s";
    public static final String USER_LOGIN_TOKEN = "SMS:USER:LOGIN:TOKEN:%s";

    public static String getStaffLoggedInKey(Integer id){
        return String.format(STAFF_LOGIN_ID, id);
    }
    public static String getStaffLoginFailureCountKey(Integer id){
        return String.format(STAFF_LOGIN_FAILURE, id);
    }
    public static String getUserLoginTokenKey(String token) {return String.format(USER_LOGIN_TOKEN, token);}

}
