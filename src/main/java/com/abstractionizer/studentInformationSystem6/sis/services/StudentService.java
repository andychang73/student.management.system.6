package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;

import java.util.Optional;
import java.util.Set;

public interface StudentService extends UserService{

    Student create(Student student);

    Optional<Student> getStudent(Integer studentId);

    boolean isStudentIdExists(Integer studentId);

    boolean areStudentIdsExist(Set<Integer> studentIds);

    void updateStudentInfo(Student student);
}
