package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    int insertBatch(@Param("user_roles")Set<UserRole> userRoles);

    int countByUserIdAndRoleIds(@Param("user_id") Integer userId, @Param("role_ids")Set<Integer> roleIds);
}
