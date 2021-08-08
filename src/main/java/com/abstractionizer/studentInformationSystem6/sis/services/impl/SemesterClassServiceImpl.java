package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.SemesterClassMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.SemesterClassService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SemesterClassServiceImpl implements SemesterClassService {

    private final SemesterClassMapper semesterClassMapper;

    @Override
    public void create(@NonNull final List<SemesterClass> classes) {
        if(classes.isEmpty()){
            return;
        }
        if(semesterClassMapper.insertBatch(classes) != classes.size()){
            log.error("Failed to create semester classes: {}", classes);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }
}
