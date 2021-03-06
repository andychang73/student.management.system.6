package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Staff;
import com.abstractionizer.studentInformationSystem6.models.vo.user.teacher.TeacherVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface StaffMapper extends BaseMapper<Staff> {

    Staff selectByStaffId(@Param("id") Integer id);

    List<TeacherVo> selectByReportTo(@Param("report_to") Integer reportTo);

    int countByStaffId(@Param("id") Integer id);

    int updateStaff(@Param("id") Integer id, @Param("password") String password, @Param("birthday") Date birthday, @Param("email") String email,
                    @Param("phone") String phone, @Param("address") String address, @Param("first_time_login") Integer firstTimeLogin, @Param("status") Integer status);

    int countByStaffIdAndReportTo(@Param("id") Integer id, @Param("report_to") Integer reportTo);
}
