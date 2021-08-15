package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Homework;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.HomeworkMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.HomeworkService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkMapper homeworkMapper;


    @Override
    public void create(@NonNull final Homework homework) {
        if(homeworkMapper.create(homework) != 1){
            log.error("Failed to create homework: {}", homework);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }

    @Override
    public boolean isHomeworkExists(@NonNull final Integer semesterClassId) {
        return homeworkMapper.countBySemesterClassId(semesterClassId) > 0;
    }
}
