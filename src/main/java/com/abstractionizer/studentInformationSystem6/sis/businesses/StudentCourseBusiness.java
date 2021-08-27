package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.studentCourse.GradingBo;

public interface StudentCourseBusiness {

    void grading(Integer staffId, GradingBo bo);
}
