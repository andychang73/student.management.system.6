package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.User;
import com.abstractionizer.studentInformationSystem6.enums.UserGroup;

import java.util.Optional;

public interface UserService<T extends User> {

    Optional<T> getUser(Integer id);

    void freezeAccount(Integer userId);

    UserGroup getGroupId();

}
