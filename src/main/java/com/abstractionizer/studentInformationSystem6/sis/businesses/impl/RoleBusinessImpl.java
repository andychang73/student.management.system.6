package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Role;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.RoleAuthority;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.role.CreateRoleBo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.RoleBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.AuthorityService;
import com.abstractionizer.studentInformationSystem6.sis.services.RoleAuthorityService;
import com.abstractionizer.studentInformationSystem6.sis.services.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RoleBusinessImpl implements RoleBusiness {

    private final RoleService roleService;
    private final RoleAuthorityService roleAuthorityService;
    private final AuthorityService authorityService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(CreateRoleBo bo) {
        if(roleService.isRoleExists(bo.getRole())){
            throw new CustomExceptions(ErrorCode.ROLE_ALREADY_EXISTS);
        }

        Integer roleId = roleService.create(new Role().setRole(bo.getRole())).getId();

        if(!authorityService.areAuthorityExist(bo.getAuthorityIds())){
            throw new CustomExceptions(ErrorCode.AUTHORITY_NOT_FOUND);
        }
        if(roleAuthorityService.areRoleAuthorityExist(roleId, bo.getAuthorityIds())){
            throw new CustomExceptions(ErrorCode.ROLE_AUTHORITY_EXISTS);
        }

        roleAuthorityService.insertBatch(getRoleAuthorities(roleId, bo.getAuthorityIds()));
    }

    private List<RoleAuthority> getRoleAuthorities(Integer roleId, Set<Integer> authorityIds){
        return authorityIds.stream()
                .map(i -> new RoleAuthority().setRoleId(roleId).setAuthorityId(i))
                .collect(Collectors.toList());
    }
}
