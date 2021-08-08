package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.abstractionizer.studentInformationSystem6.models.dto.semesterClass.ClassDateDto;

import java.util.Date;
import java.util.List;

public interface SemesterService {

    void create(Semester semester);

    boolean isSemesterExists(Integer year, Integer semester);

    boolean isSemesterEnded(Date now);

    Semester getCurrentSemester();

    Date getEndOfSemester(Date date, Integer numOfDay);

    List<ClassDateDto> getSemesterClassDates(Date startDate, Integer weekDay, Integer numOfWeeks);

}
