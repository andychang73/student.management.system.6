package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.College;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.CollegeMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.CollegeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CollegeServiceImpl implements CollegeService {

    private final CollegeMapper collegeMapper;

    @Override
    public boolean isCollegeIdExists(Integer id) {
        return collegeMapper.countByIdOrCollege(id, null) > 0;
    }

    @Override
    public boolean isCollegeExists(String college) {
        return collegeMapper.countByIdOrCollege(null, college) > 0;
    }

    @Override
    public void create(College college) {
        if(collegeMapper.insert(college) != 1){
            log.error("Create college failed : {}", college);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }
}
