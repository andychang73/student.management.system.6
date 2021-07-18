package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StaffMapper;
import com.abstractionizer.studentInformationSystem6.sis.services.StaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class StaffServiceImpl implements StaffService {

    private final StaffMapper staffMapper;
}
