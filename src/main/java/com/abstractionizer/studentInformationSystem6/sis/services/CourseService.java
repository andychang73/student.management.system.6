package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Course;
import com.abstractionizer.studentInformationSystem6.models.vo.course.CourseVo;
import java.util.List;
import java.util.Set;

public interface CourseService {

    Course create(Course course);

    List<CourseVo> getAllCourses();

    List<CourseVo> getCoursesByHeadId(Integer headId);

    boolean isCourseExists(String course);

    boolean isCourseExists(Integer courseId);

    boolean areCourseIdsExist(Set<Integer> courseIds);

    boolean isMyCourse(Integer headId, Integer courseId);
}
