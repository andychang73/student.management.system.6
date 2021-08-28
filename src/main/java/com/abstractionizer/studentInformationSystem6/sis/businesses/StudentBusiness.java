package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.user.CreateStudentBo;

public interface StudentBusiness extends UserBusiness{

    void create(String creator, CreateStudentBo bo);
}
