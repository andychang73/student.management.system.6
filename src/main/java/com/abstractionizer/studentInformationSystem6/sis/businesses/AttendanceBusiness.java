package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.attendance.TakeAttendanceBo;

public interface AttendanceBusiness {

    void takeAttendance(String teacher, TakeAttendanceBo bo);
}
