package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.constants.ProjectConstant;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentClass;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.studentClass.EnrollBo;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentClassBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentClassBusinessImpl implements StudentClassBusiness {

    private final StudentClassService studentClassService;
    private final StudentCourseService studentCourseService;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enroll(@NonNull final Integer studentId, @NonNull final EnrollBo bo) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }

        Semester semester = semesterService.getCurrentSemester();

        if(semester.getEndDate().before(dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.INVALID_SEMESTER, "New semester has not yet been created, please contact admin");
        }
        if(!classService.isClassValid(bo.getClassId(), semester.getId())){
            throw new CustomExceptions(ErrorCode.CLASS_INVALID);
        }
        if(!classService.isAllowedToEnroll(dateConfigService.getDate(), semester.getStartDate())){
            throw new CustomExceptions(ErrorCode.CLASS_ENROLLMENT_NOT_ALLOWED);
        }
        if(classService.hasEnrolled(studentId, bo.getClassId())){
            throw new CustomExceptions(ErrorCode.CLASS_ENROLLED);
        }

        Classes classes = classService.getClass(bo.getClassId()).orElseThrow(() -> new CustomExceptions(ErrorCode.CLASS_UNAVAILABLE));

        enrollByClass(classes);
        studentCourseService.updateStudentCourseStatus(studentId, classes.getCourseId(), ProjectConstant.StudentCourseStatus.IN_PROGRESS);

        studentClassService.create(new StudentClass()
                .setClassId(bo.getClassId())
                .setStudentId(studentId));
    }

    private void enrollByClass(Classes classes){
        Classes tmp = classes;
        while(!classService.enroll(tmp.getId(), tmp.getVersion())){
            tmp = classService.getClass(tmp.getId()).orElseThrow(() -> new CustomExceptions(ErrorCode.CLASS_UNAVAILABLE));
        }
    }
}
