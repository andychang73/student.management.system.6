package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentMajor;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StudentMajorMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentMajorService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class StudentMajorServiceImpl implements StudentMajorService {

    private final StudentMajorMapper studentMajorMapper;

    @Override
    public void create(@NonNull final StudentMajor studentMajor) {
        if(studentMajorMapper.insert(studentMajor) != 1){
            log.error("Failed to create student major: {}", studentMajor);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }

    @Override
    public boolean isStudentMajorExists(@NonNull final Integer studentId, @NonNull final Integer majorId) {
        return studentMajorMapper.countByStudentIdAndMajorId(studentId, majorId) > 0;
    }
}
