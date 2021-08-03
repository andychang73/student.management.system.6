package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    int insertBatch(@Param("student_courses")List<StudentCourse> studentCourses);
}
