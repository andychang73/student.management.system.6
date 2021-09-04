package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Homework;
import com.abstractionizer.studentInformationSystem6.models.dto.homework.HomeworkDto;
import java.util.Date;
import java.util.List;


public interface HomeworkService {

    void create(Homework homework);

    boolean isHomeworkExists(Integer semesterClassId);

    List<HomeworkDto> getValidHomeworks(Integer classId, Date now);
}
