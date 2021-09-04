package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Homework;
import com.abstractionizer.studentInformationSystem6.models.dto.homework.HomeworkDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Mapper
@Repository
public interface HomeworkMapper extends BaseMapper<Homework> {

    int countBySemesterClassId(@Param("semester_class_id") Integer semesterClassId);

    List<HomeworkDto> selectHomeworksByClassIdAndDeadline(@Param("class_id") Integer classId, @Param("now") Date now);

    String selectAnswersBySemesterClassIdString(@Param("semester_class_id") Integer semesterClassId);
}
