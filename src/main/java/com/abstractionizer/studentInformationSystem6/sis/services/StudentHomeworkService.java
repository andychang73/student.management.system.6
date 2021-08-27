package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentWeeklyHomeworkGradeVo;

import java.util.Date;
import java.util.List;

public interface StudentHomeworkService {

    List<StudentWeeklyHomeworkGradeVo> getAllHomeWorkGrades (Integer classId, Date now);
}
