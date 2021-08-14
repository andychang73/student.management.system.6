package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Attendance;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.attendance.TakeAttendanceBo;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.sis.businesses.AttendanceBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.AttendanceService;
import com.abstractionizer.studentInformationSystem6.sis.services.DateConfigService;
import com.abstractionizer.studentInformationSystem6.sis.services.SemesterClassService;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentClassService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AttendanceBusinessImpl implements AttendanceBusiness {

    private final SemesterClassService semesterClassService;
    private final DateConfigService dateConfigService;
    private final StudentClassService studentClassService;
    private final AttendanceService attendanceService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void takeAttendance(@NonNull final String teacher, @NonNull final TakeAttendanceBo bo) {
        SemesterClass semesterClass = semesterClassService.selectBySemesterClassId(bo.getClassId()).orElseThrow(()-> new CustomExceptions(ErrorCode.CLASS_NON_EXISTS));

        if(attendanceService.hasAttendanceTaken(bo.getClassId())){
            throw new CustomExceptions(ErrorCode.ATTENDANCE_TAKEN);
        }
        if(!studentClassService.areStudentsInTheClass(semesterClass.getClassId(), bo.getStudentIds())){
            throw new CustomExceptions(ErrorCode.CLASS_STUDENT_NOT_FOUND);
        }
        if(!dateConfigService.getDate().after(semesterClassService.getClassEndTime(bo.getClassId()))){
            throw new CustomExceptions(ErrorCode.ATTENDANCE_FORBIDDEN);
        }

        attendanceService.create(getAttendances(bo.getClassId(), bo.getStudentIds(), teacher));

        List<AttendanceDto> dto = attendanceService.getStudentCurrentAttendances(semesterClass.getClassId(), semesterClass.getWeekNo());

        studentClassService.updateCurrentAttendance(semesterClass.getClassId(), dto);
    }

    private List<Attendance> getAttendances(Integer classId, Set<Integer> studentIds, String teacher){
        return studentIds.stream()
                .map(s -> new Attendance()
                        .setSemesterClassId(classId)
                        .setStudentId(s)
                        .setTakenBy(teacher))
                .collect(Collectors.toList());
    }
}
