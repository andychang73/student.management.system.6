package com.abstractionizer.studentInformationSystem6.sis.services;


import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.RoleAuthority;

import java.util.List;
import java.util.Set;

public interface RoleAuthorityService {

    boolean areRoleAuthorityExist(Integer roleId, Set<Integer> authorityIds);

    void insertBatch(List<RoleAuthority> roleAuthorities);
}
