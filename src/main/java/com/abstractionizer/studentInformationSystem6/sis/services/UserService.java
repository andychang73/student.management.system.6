package com.abstractionizer.studentInformationSystem6.sis.services;


public interface UserService {

    void freezeAccount(Integer userId);

    void firstTimeChangePassword(Integer userId, String password);

    void changePassword(Integer userId, String password);

}
