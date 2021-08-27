package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentHomework;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentAverageGradesVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentWeeklyHomeworkGradeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface StudentHomeworkMapper extends BaseMapper<StudentHomework> {

    List<StudentWeeklyHomeworkGradeVo> selectByClassIdAndDeadLine(@Param("class_id") Integer classId, @Param("now") Date now);

}
