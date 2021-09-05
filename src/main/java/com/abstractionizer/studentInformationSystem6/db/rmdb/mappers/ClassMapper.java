package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.abstractionizer.studentInformationSystem6.models.dto.studentHomework.WeekNoAndGrade;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassWithoutCourseVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentAverageGradesVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface ClassMapper extends BaseMapper<Classes> {

    int countByStaffIdAndStartTimeAndEndTime(@Param("staff_id") Integer staffId, @Param("week_day") Integer weekDay, @Param("start_time")Time startTime, @Param("end_time")Time endTime);

    ClassesOfTheWeekVo selectClassDtoBySemesterIdAndCourseId(@Param("semester_id") Integer semesterId, @Param("course_id") Integer courseId);

    ClassInfoVo selectByClassId(@Param("id") Integer id);

    int countByClassIdAndSemesterId(@Param("id") Integer id, @Param("semester_id") Integer semesterId);

    int countByClassIdAndOrHeadId(@Param("id") Integer id, @Param("head_id") Integer headId);

    int countByClassIdAndStaffIdAndDate(@Param("id") Integer id, @Param("staff_id") Integer staffId, @Param("date") Date date);

    List<ClassVo> selectByStaffIdAndSemesterId(@Param("staff_id") Integer staffId, @Param("semester_id") Integer semesterId);

    List<StudentAverageGradesVo> selectStudentAverageGradeByClassId(@Param("id") Integer id, @Param("number_of_homework") Integer numberOfHomework);

    int countNumberOfHomeworkByClassId(@Param("id") Integer id);

    List<StudentCourse> selectByClassIdAndStudentId(@Param("id") Integer id, @Param("semester_id") Integer semesterId, @Param("student_ids") Set<Integer> studentIds);

    List<WeekNoAndGrade> selectHomeWorkGradesByClassIdAndStudentId(@Param("student_id") Integer id, @Param("id") Integer classId);

    List<ClassWithoutCourseVo> selectByCourseIdAndSemesterId(@Param("course_id") Integer courseId, @Param("semester_id") Integer semester_id);

    int updateNumOfStudentEnrolled(@Param("id") Integer id, @Param("version") Integer version);

    Classes getById(@Param("id") Integer id);

    int countByCourseIdAndStudentId(@Param("student_id") Integer studentId, @Param("id") Integer id);
}
