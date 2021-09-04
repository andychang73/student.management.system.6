package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.models.vo.attendance.AttendanceVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentCourseVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentClassBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentClassBusinessImpl implements StudentClassBusiness {

    private final StudentClassService studentClassService;
    private final StudentService studentService;
    private final AttendanceService attendanceService;
    private final DateConfigService dateConfigService;
    private final SemesterService semesterService;
    private final ClassService classService;

    @Override
    public StudentsOfTheClass getStudentsOfTheClass(@NonNull final Integer staffId, @NonNull final Integer classId) {
        if(!classService.isClassValid(classId,staffId, dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.CLASS_INVALID);
        }

        Integer weekNo = semesterService.getWeekNumber(dateConfigService.getDate());

        SemesterClass semesterClass = studentClassService.getSemesterClass(classId, weekNo).orElseThrow(() -> new CustomExceptions(ErrorCode.CLASS_NON_EXISTS));

        if(attendanceService.hasAttendanceTaken(semesterClass.getId())){
            throw new CustomExceptions(ErrorCode.ATTENDANCE_TAKEN);
        }

        return studentClassService.getStudentsOfTheClass(classId, weekNo);
    }

    @Override
    public List<AttendanceDto> getStudentsCurrentAttendance(@NonNull final Integer staffId, @NonNull final Integer classId) {
        if(!classService.isClassValid(classId, staffId, dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.CLASS_INVALID);
        }
        return studentClassService.getStudentsCurrentAttendance(classId);
    }

    @Override
    public List<ClassVo> getClassScheduleOfThisSemester(@NonNull final Integer studentId) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }
        return studentClassService.getScheduleOfThisSemester(studentId, semesterService.getCurrentSemester().getId());
    }
}
