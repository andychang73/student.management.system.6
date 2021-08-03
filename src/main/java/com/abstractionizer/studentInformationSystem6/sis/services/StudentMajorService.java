package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentMajor;

public interface StudentMajorService {

    void create(StudentMajor studentMajor);

    boolean isStudentMajorExists(Integer studentId, Integer majorId);
}
