package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.studentCourse.GradingBo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentCourseVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentIdAndCourseInfoVo;

import java.util.List;

public interface StudentCourseBusiness {

    void grading(Integer staffId, GradingBo bo);

    List<StudentCourseVo> getStudentCourseStatus(Integer studentId);

    List<StudentIdAndCourseInfoVo> getAvailableCourses(Integer studentId);
}
