package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Attendance;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttendanceMapper extends BaseMapper<Attendance> {

    int countBySemesterClassId(@Param("semester_class_id") Integer semesterClassId);

    int insertBatch(@Param("attendances")List<Attendance> attendances);

    List<AttendanceDto> selectByClassIdAndWeekNo(@Param("class_id") Integer classId, @Param("week_no") Integer weekNo);
}
