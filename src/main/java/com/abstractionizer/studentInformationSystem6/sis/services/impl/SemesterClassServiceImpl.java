package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.SemesterClassMapper;
import com.abstractionizer.studentInformationSystem6.sis.services.SemesterClassService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SemesterClassServiceImpl implements SemesterClassService {

    private final SemesterClassMapper semesterClassMapper;
}
