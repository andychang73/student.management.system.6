package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.MajorCourseMapper;
import com.abstractionizer.studentInformationSystem6.sis.services.MajorCourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MajorCourseServiceImpl implements MajorCourseService {

    private final MajorCourseMapper majorCourseMapper;
}
