package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.MajorCourse;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.MajorCourseMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.MajorCourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class MajorCourseServiceImpl implements MajorCourseService {

    private final MajorCourseMapper majorCourseMapper;

    @Override
    public void create(Set<MajorCourse> majorCourses) {
        if(majorCourses.isEmpty()){
            return;
        }
        if(majorCourseMapper.insertBatch(majorCourses) != majorCourses.size()){
            log.error("Failed to create major courses: {}", majorCourses);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }
}
