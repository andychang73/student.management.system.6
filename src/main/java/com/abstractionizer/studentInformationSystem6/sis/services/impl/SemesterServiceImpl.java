package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.SemesterMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.SemesterService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    private final SemesterMapper semesterMapper;


    @Override
    public void create(@NonNull final Semester semester) {
        if(semesterMapper.insert(semester) != 1){
            log.error("Failed to create semester: {}", semester);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }

    @Override
    public boolean isSemesterExists(@NonNull final Integer year, @NonNull final Integer semester) {
        return semesterMapper.countByYearAndSemester(year, semester) > 0;
    }
}
