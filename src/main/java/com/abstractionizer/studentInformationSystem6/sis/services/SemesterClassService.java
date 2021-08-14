package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SemesterClassService {

    void create(List<SemesterClass> classes);

    Optional<SemesterClass> selectBySemesterClassId(Integer id);

    Date getClassEndTime(Integer id);

}
