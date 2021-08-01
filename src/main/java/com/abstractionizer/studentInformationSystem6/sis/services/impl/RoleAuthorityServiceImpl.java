package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.RoleAuthority;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.RoleAuthorityMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.RoleAuthorityService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    private final RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public boolean areRoleAuthorityExist(@NonNull final Integer roleId, @NonNull final Set<Integer> authorityIds) {
        if(authorityIds.isEmpty()){
            return false;
        }
        return roleAuthorityMapper.countByRoleIdsAndAuthorityIds(roleId, authorityIds).equals(authorityIds.size());
    }

    @Override
    public void insertBatch(@NonNull final List<RoleAuthority> roleAuthorities) {
        if(roleAuthorities.isEmpty()){
            return;
        }
        if(roleAuthorityMapper.insertBatch(roleAuthorities) != roleAuthorities.size()){
            log.error("Insert role authorities failed, role authorities: {}", roleAuthorities);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }
}
