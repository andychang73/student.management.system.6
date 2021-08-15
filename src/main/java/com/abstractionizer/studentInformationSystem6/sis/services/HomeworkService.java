package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Homework;

public interface HomeworkService {

    void create(Homework homework);

    boolean isHomeworkExists(Integer semesterClassId);
}
