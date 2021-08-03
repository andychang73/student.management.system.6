package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;

public interface SemesterService {

    void create(Semester semester);

    boolean isSemesterExists(Integer year, Integer semester);
}
