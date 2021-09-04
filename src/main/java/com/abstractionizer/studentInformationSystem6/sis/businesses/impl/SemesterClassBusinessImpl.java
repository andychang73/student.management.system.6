package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.vo.attendance.AttendanceVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.SemesterClassBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SemesterClassBusinessImpl implements SemesterClassBusiness {

    private final SemesterClassService semesterClassService;
    private final SemesterService semesterService;
    private final StudentClassService studentClassService;
    private final StudentService studentService;
    private final ClassService classService;

    @Override
    public List<AttendanceVo> getClassAttendance(@NonNull final Integer studentId, @NonNull final Integer classId) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }
        if(!classService.isClassValid(classId, semesterService.getCurrentSemester().getId())){
            throw new CustomExceptions(ErrorCode.CLASS_NON_EXISTS);
        }
        if(!studentClassService.isStudentInTheClass(classId, studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NOT_IN_CLASS);
        }
        return semesterClassService.getAttendanceOfTheClass(studentId, classId);
    }
}
