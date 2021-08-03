package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.user.CreateStudentBo;

public interface StudentBusiness {

    void create(String creator, CreateStudentBo bo);
}
