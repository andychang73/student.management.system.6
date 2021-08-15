package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;
import com.abstractionizer.studentInformationSystem6.models.bo.user.CreateStudentBo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentBusiness;
import com.abstractionizer.studentInformationSystem6.sis.businesses.BaseBusinesses.UserBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.LoginService;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentService;
import com.abstractionizer.studentInformationSystem6.utils.MD5Util;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentBusinessImpl extends UserBusiness<Student> implements StudentBusiness {

    private final StudentService studentService;

    public StudentBusinessImpl(StudentService studentService, LoginService loginService){
        super(loginService);
        this.studentService = studentService;
    }

    @Override
    public void create(@NonNull final String creator, @NonNull final CreateStudentBo bo) {
        Student student = new Student(
                MD5Util.md5(this.generateDefaultPassword(bo.getBirthday())),
                bo.getUsername(),
                bo.getBirthday(),
                bo.getEmail(),
                bo.getPhone(),
                bo.getAddress(),
                creator
        );
        studentService.create(student);
    }
}
