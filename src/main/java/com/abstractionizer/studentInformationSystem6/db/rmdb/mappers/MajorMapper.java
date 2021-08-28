package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Major;
import com.abstractionizer.studentInformationSystem6.models.dto.course.PreCourseCountDto;
import com.abstractionizer.studentInformationSystem6.models.vo.major.MajorVo;
import com.abstractionizer.studentInformationSystem6.models.vo.majorCourseVo.MajorCourseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface MajorMapper extends BaseMapper<Major> {

    int countByMajorIdOrMajor(@Param("major_ids")Set<Integer> majorIds, @Param("major") String major);

    List<PreCourseCountDto> getNumOfPreCourse(@Param("id") Integer majorId);

    List<MajorVo> getAllMajors();

    List<MajorCourseVo> selectAllMajorAndCourses();
}
