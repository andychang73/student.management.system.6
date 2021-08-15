package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentClassService {

    Optional<SemesterClass> getSemesterClass(Integer classId, Integer weekNo);

    StudentsOfTheClass getStudentsOfTheClass(Integer classId, Integer weekNo);
    
    boolean areStudentsInTheClass(Integer classId, Set<Integer> studentIds);

    void updateCurrentAttendance(Integer classId, List<AttendanceDto> dto);

    List<AttendanceDto> getStudentsCurrentAttendance(Integer classId);
}
