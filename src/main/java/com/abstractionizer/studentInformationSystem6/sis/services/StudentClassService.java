package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentClass;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentClassService {

    void create(StudentClass studentClass);

    Optional<SemesterClass> getSemesterClass(Integer classId, Integer weekNo);

    StudentsOfTheClass getStudentsOfTheClass(Integer classId, Integer weekNo);
    
    boolean areStudentsInTheClass(Integer classId, Set<Integer> studentIds);

    boolean isStudentInTheClass(Integer classId, Integer studentId);

    void updateCurrentAttendance(Integer classId, List<AttendanceDto> dto);

    List<AttendanceDto> getStudentsCurrentAttendance(Integer classId);

    Float getAttendance(Integer studentId, Integer classId, Integer semesterId);

    List<ClassVo> getScheduleOfThisSemester(Integer studentId, Integer semesterId);
}
