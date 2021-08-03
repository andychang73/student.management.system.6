package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;

import java.util.Set;

public interface StudentService {

    Student create(Student student);

    boolean isStudentIdExists(Integer studentId);

    boolean areStudentIdsExist(Set<Integer> studentIds);
}
