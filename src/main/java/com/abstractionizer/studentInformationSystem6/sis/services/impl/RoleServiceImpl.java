package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Role;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.RoleMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public boolean isRoleExists(Integer roleId) {
        return roleMapper.countByRoleIdOrRole(roleId, null) > 0;
    }

    @Override
    public boolean isRoleExists(String role) {
        return roleMapper.countByRoleIdOrRole(null, role) > 0;
    }

    @Override
    public Role create(Role role) {
        if(roleMapper.insert(role) != 1){
            log.error("Role service create role failed");
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return role;
    }
}
