package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.course.CreateCourseBo;
import com.abstractionizer.studentInformationSystem6.models.vo.course.CourseVo;
import java.util.List;

public interface CourseBusiness {

    void create(String creator, CreateCourseBo bo);

    List<CourseVo> getAllCourses();

    List<CourseVo> getMyCourses(Integer headId);
}
