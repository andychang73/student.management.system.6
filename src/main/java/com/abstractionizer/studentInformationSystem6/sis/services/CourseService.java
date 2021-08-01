package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Course;

import java.util.Set;

public interface CourseService {

    Course create(Course course);

    boolean isCourseExists(String course);

    boolean areCourseIdsExist(Set<Integer> courseIds);
}
