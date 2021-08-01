package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.MajorCourse;

import java.util.Set;

public interface MajorCourseService {

    void create(Set<MajorCourse> majorCourses);
}
