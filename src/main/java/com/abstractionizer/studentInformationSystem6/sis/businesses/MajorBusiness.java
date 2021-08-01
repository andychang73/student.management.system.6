package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.major.CreateMajorBo;

public interface MajorBusiness {

    void create(String creator, CreateMajorBo bo);
}
