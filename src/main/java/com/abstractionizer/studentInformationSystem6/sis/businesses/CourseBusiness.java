package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.course.CreateCourseBo;

public interface CourseBusiness {

    void create(String creator, CreateCourseBo bo);
}
