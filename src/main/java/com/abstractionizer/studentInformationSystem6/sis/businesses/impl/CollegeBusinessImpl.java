package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.College;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.college.CreateCollegeBo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.CollegeBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.CollegeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CollegeBusinessImpl implements CollegeBusiness {

    private final CollegeService collegeService;

    @Override
    public void create(String creator, CreateCollegeBo bo) {
        if(collegeService.isCollegeExists(bo.getCollege())){
            throw new CustomExceptions(ErrorCode.COLLEGE_ALREADY_EXISTS);
        }

        collegeService.create(new College().setCollege(bo.getCollege()).setCreator(creator));
    }
}
