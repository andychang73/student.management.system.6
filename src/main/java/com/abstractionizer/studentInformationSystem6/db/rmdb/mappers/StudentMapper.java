package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    int countByStudentIds(@Param("student_ids")Set<Integer> studentIds);

    Student selectByStudentId(@Param("student_id") Integer studentId);

    int updateStudent(@Param("id") Integer id, @Param("password") String password, @Param("birthday") Date birthday, @Param("email") String email,
                    @Param("phone") String phone, @Param("address") String address, @Param("first_time_login") Integer firstTimeLogin, @Param("status") Integer status);
}
