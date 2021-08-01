package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.UserRole;
import java.util.Set;

public interface UserRoleService {

    boolean areUserRoleExist(Integer userId, Set<Integer> roleIds);

    void create(Set<UserRole> userRoles);
}
