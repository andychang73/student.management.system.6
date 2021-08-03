package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;

import java.util.List;

public interface StudentCourseService {

    void create(List<StudentCourse> studentCourses);
}
