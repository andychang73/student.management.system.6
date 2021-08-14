package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Mapper
@Repository
public interface ClassMapper extends BaseMapper<Classes> {

    int countByStaffIdAndStartTimeAndEndTime(@Param("staff_id") Integer staffId, @Param("week_day") Integer weekDay, @Param("start_time")Time startTime, @Param("end_time")Time endTime);

    ClassesOfTheWeekVo selectClassDtoBySemesterIdAndCourseId(@Param("semester_id") Integer semesterId, @Param("course_id") Integer courseId);

    ClassInfoVo selectByClassId(@Param("id") Integer id);

    int countByClassIdAndOrHeadId(@Param("id") Integer id, @Param("head_id") Integer headId);

    int countByClassIdAndStaffId(@Param("id") Integer id, @Param("staff_id") Integer staffId);

    List<ClassVo> selectByStaffIdAndSemesterId(@Param("staff_id") Integer staffId, @Param("semester_id") Integer semesterId);
}
