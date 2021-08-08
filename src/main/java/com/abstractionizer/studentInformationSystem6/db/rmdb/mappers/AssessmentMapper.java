package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Assessment;
import com.abstractionizer.studentInformationSystem6.models.vo.assessment.AssessmentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AssessmentMapper extends BaseMapper<Assessment> {

    AssessmentVo selectByStaffId(@Param("staff_id") Integer staffId);

    int countBySemesterIdAndStaffId(@Param("semester_id") Integer semesterId, @Param("staff_id") Integer staffId);
}
