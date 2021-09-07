package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.UserRole;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.UserRoleMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.UserRoleService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Override
    public boolean areUserRoleExist(@NonNull final Integer userId, @NonNull final Set<Integer> roleIds) {
        return userRoleMapper.countByUserIdAndRoleIds(userId, roleIds) == roleIds.size();
    }

    @Override
    public boolean isUserMatchingRole(@NonNull final Integer userId, @NonNull final Integer roleId) {
        return userRoleMapper.countByUserIdAndRoleIds(userId, Set.of(roleId)) > 0;
    }

    @Override
    public void create(@NonNull final Set<UserRole> userRoles) {
        if(userRoles.isEmpty()){
            return;
        }
        if(userRoleMapper.insertBatch(userRoles) != userRoles.size()){
            log.error("Create user roles failed: {}", userRoles);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }
}
