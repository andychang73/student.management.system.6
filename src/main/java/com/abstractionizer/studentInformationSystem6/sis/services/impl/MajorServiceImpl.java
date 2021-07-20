package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.MajorMapper;
import com.abstractionizer.studentInformationSystem6.sis.services.MajorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MajorServiceImpl implements MajorService {

    private final MajorMapper majorMapper;
}
