package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.College;

public interface CollegeService {

    boolean isCollegeIdExists(Integer id);

    boolean isCollegeExists(String college);

    void create(College college);
}
