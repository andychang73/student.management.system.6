package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.homework.CreateHomeworkBo;

public interface HomeworkBusiness {

    void create(Integer staffId, CreateHomeworkBo bo);
}
