package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StudentMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;


    @Override
    public Student create(@NonNull final Student student) {
        if(studentMapper.insert(student) != 1){
            log.error("Failed to create student: {}", student);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return student;
    }

    @Override
    public boolean isStudentIdExists(Integer studentId) {
        return studentMapper.countByStudentIds(Set.of(studentId)) > 0;
    }

    @Override
    public boolean areStudentIdsExist(Set<Integer> studentIds) {
        return studentMapper.countByStudentIds(studentIds) == studentIds.size();
    }
}
