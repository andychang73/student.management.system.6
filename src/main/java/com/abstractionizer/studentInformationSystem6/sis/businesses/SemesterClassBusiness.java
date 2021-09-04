package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.vo.attendance.AttendanceVo;

import java.util.List;

public interface SemesterClassBusiness {

    List<AttendanceVo> getClassAttendance(Integer studentId, Integer classId);
}
