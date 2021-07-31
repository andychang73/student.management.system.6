package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Role;

public interface RoleService {

    boolean isRoleExists(Integer roleId);

    boolean isRoleExists(String role);

    Role create(Role role);
}
