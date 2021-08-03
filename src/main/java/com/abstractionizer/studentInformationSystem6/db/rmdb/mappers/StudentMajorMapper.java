package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentMajor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMajorMapper extends BaseMapper<StudentMajor> {

    int countByStudentIdAndMajorId(@Param("student_id") Integer studentId, @Param("major_id") Integer majorId);
}
