package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.semester.CreateSemesterBo;

public interface SemesterBusiness {

    void create(String creator, CreateSemesterBo bo);
}
