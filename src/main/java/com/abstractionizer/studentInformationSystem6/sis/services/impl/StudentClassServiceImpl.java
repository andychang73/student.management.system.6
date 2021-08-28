package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StudentClassMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentClassService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class StudentClassServiceImpl implements StudentClassService {

    private final StudentClassMapper studentClassMapper;

    @Override
    public Optional<SemesterClass> getSemesterClass(@NonNull final Integer classId, @NonNull final Integer weekNo) {
        return Optional.ofNullable(studentClassMapper.selectByClassIdAndWeekNum(classId, weekNo));
    }

    @Override
    public StudentsOfTheClass getStudentsOfTheClass(@NonNull final Integer classId, @NonNull final Integer weekNo) {
        return studentClassMapper.selectByClassIdAndWeekNo(classId, weekNo);
    }

    @Override
    public boolean areStudentsInTheClass(Integer classId, Set<Integer> studentIds) {
        return studentClassMapper.countByStudentIdAndClassId(classId, studentIds) == studentIds.size();
    }

    @Override
    public void updateCurrentAttendance(@NonNull final Integer classId, @NonNull final List<AttendanceDto> dto) {
        if(studentClassMapper.updateAttendanceByClassIdAndStudentId(classId, dto) != dto.size()){
            log.error("Update student class failed, class id : {}, dto: {}", classId, dto);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public List<AttendanceDto> getStudentsCurrentAttendance(@NonNull final Integer classId) {
        return studentClassMapper.selectAttendanceByClassId(classId);
    }

    @Override
    public Float getAttendance(@NonNull final Integer studentId, @NonNull final Integer classId, @NonNull final Integer semesterId) {
        return studentClassMapper.selectAttendanceByStudentIdAndClassIdAndSemesterId(studentId, classId, semesterId);
    }
}
