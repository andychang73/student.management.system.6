package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.PreCourseMapper;
import com.abstractionizer.studentInformationSystem6.sis.services.PreCourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PreCourseServiceImpl implements PreCourseService {

    private final PreCourseMapper preCourseMapper;
}
