package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Attendance;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;

import java.util.List;

public interface AttendanceService {

    boolean hasAttendanceTaken(Integer semesterClassId);

    void create(List<Attendance> attendances);

    List<AttendanceDto> getStudentCurrentAttendances(Integer classId, Integer weekNo);
}
