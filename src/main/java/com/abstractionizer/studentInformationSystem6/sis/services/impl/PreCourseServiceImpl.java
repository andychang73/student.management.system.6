package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.PreCourse;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.PreCourseMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.PreCourseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class PreCourseServiceImpl implements PreCourseService {

    private final PreCourseMapper preCourseMapper;


    @Override
    public void create(@NonNull final Set<PreCourse> preCourses) {
        if(preCourses.isEmpty()){
            return;
        }
        if(preCourseMapper.insertBatch(preCourses) != preCourses.size()){
            log.error("Create pre courses failed: {}", preCourses);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }
}
