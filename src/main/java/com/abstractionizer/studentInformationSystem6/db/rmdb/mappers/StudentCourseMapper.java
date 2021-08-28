package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentCourseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    int insertBatch(@Param("student_courses")List<StudentCourse> studentCourses);

    int updateFinalGradeAndFinalAttendance(@Param("student_course") StudentCourse studentCourses);

    void updateAndSelectByStudentIdAndClassId(@Param("student_id") Integer studentId, @Param("class_id") Integer classId);

    void updateStatusByNumOfCompletedPreCourse(@Param("student_id") Integer studentId);

    List<StudentCourseVo> selectByStudentId(@Param("student_id") Integer studentId);
}
