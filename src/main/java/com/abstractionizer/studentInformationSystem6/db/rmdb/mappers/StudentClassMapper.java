package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentClass;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface StudentClassMapper extends BaseMapper<StudentClass> {

    StudentsOfTheClass selectByClassIdAndWeekNo(@Param("class_id") Integer classId, @Param("week_no") Integer weekNo);

    SemesterClass selectByClassIdAndWeekNum(@Param("class_id") Integer classid, @Param("week_no") Integer weekNo);

    int countByStudentIdAndClassId(@Param("class_id") Integer classId, @Param("student_ids") Set<Integer> studentIds);

    int updateAttendanceByClassIdAndStudentId(@Param("class_id") Integer classId, @Param("dto")List<AttendanceDto> dto);

    List<AttendanceDto> selectAttendanceByClassId(@Param("class_id") Integer classId);

    Float selectAttendanceByStudentIdAndClassIdAndSemesterId(@Param("student_id") Integer studentId, @Param("class_id") Integer classId, @Param("semester_id") Integer semesterId);
}
