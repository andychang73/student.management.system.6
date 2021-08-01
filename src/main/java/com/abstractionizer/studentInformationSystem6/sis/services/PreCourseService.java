package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.PreCourse;

import java.util.Set;

public interface PreCourseService {

    void create(Set<PreCourse> preCourses);
}
