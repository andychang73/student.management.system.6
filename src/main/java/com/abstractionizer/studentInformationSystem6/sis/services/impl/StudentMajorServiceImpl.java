package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StudentMajorMapper;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentMajorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class StudentMajorServiceImpl implements StudentMajorService {

    private final StudentMajorMapper studentMajorMapper;
}
