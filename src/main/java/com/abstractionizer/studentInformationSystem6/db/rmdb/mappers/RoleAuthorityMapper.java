package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.RoleAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface RoleAuthorityMapper extends BaseMapper<RoleAuthority> {

    Integer countByRoleIdsAndAuthorityIds(@Param("roleId") Integer roleId, @Param("authority_ids")Set<Integer> authorityIds);

    int insertBatch(@Param("role_authorities")List<RoleAuthority> roleAuthorities);
}
