package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Attendance;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.AttendanceMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.sis.services.AttendanceService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;

    @Override
    public boolean hasAttendanceTaken(@NonNull final Integer semesterClassId) {
        return attendanceMapper.countBySemesterClassId(semesterClassId) > 0;
    }

    @Override
    public void create(@NonNull final List<Attendance> attendances) {
        if(attendances.isEmpty()){
            return;
        }
        if(attendanceMapper.insertBatch(attendances) != attendances.size()){
            log.error("Failed to create attendances");
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }

    @Override
    public List<AttendanceDto> getStudentCurrentAttendances(@NonNull final Integer classId, @NonNull final Integer weekNo) {
        return attendanceMapper.selectByClassIdAndWeekNo(classId, weekNo);
    }
}
