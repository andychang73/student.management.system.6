package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Major;
import com.abstractionizer.studentInformationSystem6.models.dto.course.PreCourseCountDto;
import com.abstractionizer.studentInformationSystem6.models.vo.major.MajorVo;
import com.abstractionizer.studentInformationSystem6.models.vo.majorCourseVo.MajorCourseVo;

import java.util.List;
import java.util.Set;

public interface MajorService {

    Major create(Major major);

    List<MajorVo> getAllMajors();

    boolean isMajorExists(String major);

    boolean areMajorIdsExist(Set<Integer> majorIds);

    List<PreCourseCountDto> getPreCourseCount(Integer majorId);

    List<MajorCourseVo> getAllMajorsAndCourses();
}
