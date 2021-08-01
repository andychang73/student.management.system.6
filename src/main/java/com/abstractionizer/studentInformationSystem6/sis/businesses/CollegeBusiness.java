package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.college.CreateCollegeBo;

public interface CollegeBusiness {

    void create(String creator, CreateCollegeBo bo);
}
