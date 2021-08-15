package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Homework;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HomeworkMapper extends BaseMapper<Homework> {

    int countBySemesterClassId(@Param("semester_class_id") Integer semesterClassId);

    int create(@Param("homework")Homework homework);
}
