package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SemesterMapper extends BaseMapper<Semester> {

    int countByYearAndSemester(@Param("year") Integer year, @Param("semester") Integer semester);

    Semester selectCurrentSemester();
}
