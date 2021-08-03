package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    int countByStudentIds(@Param("student_ids")Set<Integer> studentIds);

}
