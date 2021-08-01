package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Role;

import java.util.Set;

public interface RoleService {

    boolean areRoleIdsExist(Set<Integer> roleIds);

    boolean isRoleExists(String role);

    Role create(Role role);
}
