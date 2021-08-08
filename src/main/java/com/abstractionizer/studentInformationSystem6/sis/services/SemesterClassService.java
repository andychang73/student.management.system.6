package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;

import java.util.List;

public interface SemesterClassService {

    void create(List<SemesterClass> classes);
}
