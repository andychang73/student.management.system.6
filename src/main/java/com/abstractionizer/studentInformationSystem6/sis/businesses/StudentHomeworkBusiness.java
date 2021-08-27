package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentAverageGradesVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentWeeklyHomeworkGradeVo;

import java.util.List;

public interface StudentHomeworkBusiness {

    List<StudentWeeklyHomeworkGradeVo> getAllHomeworkGrades(Integer staffId, Integer classId);

    List<StudentAverageGradesVo> getStudentAverageGrades(Integer staffId, Integer classId);
}
