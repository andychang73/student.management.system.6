package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StudentMapper;
import com.abstractionizer.studentInformationSystem6.enums.UserGroup;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentService;
import com.abstractionizer.studentInformationSystem6.sis.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;


}
