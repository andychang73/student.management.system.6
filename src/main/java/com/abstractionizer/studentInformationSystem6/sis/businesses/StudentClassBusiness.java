package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.models.vo.attendance.AttendanceVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;

import java.util.List;

public interface StudentClassBusiness {

    StudentsOfTheClass getStudentsOfTheClass(Integer staffId, Integer classId);

    List<AttendanceDto> getStudentsCurrentAttendance(Integer staffId, Integer classId);

    List<ClassVo> getClassScheduleOfThisSemester(Integer studentId);
}
