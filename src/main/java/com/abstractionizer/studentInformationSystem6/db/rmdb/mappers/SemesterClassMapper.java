package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.models.vo.attendance.AttendanceVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface SemesterClassMapper extends BaseMapper<SemesterClass> {

    int insertBatch(@Param("classes")List<SemesterClass> classes);

    SemesterClass selectBySemesterClassId(@Param("id") Integer id);

    Date selectByClassId(@Param("id") Integer id);

    List<AttendanceVo> selectByClassIdAndStudentId(@Param("student_id") Integer studentId, @Param("class_id") Integer classId);
}
