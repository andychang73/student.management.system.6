package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;

public interface StudentClassBusiness {

    StudentsOfTheClass getStudentsOfTheClass(Integer staffId, Integer classId);
}
