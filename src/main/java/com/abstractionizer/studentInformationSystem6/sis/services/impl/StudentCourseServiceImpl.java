package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StudentCourseMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentCourseVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentIdAndCourseInfoVo;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentCourseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseMapper studentCourseMapper;

    @Override
    public void create(@NonNull final List<StudentCourse> studentCourses) {
        if(studentCourses.isEmpty()){
            return;
        }
        if(studentCourseMapper.insertBatch(studentCourses) != studentCourses.size()){
            log.error("Create student course failed: {}", studentCourses);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }

    @Override
    public void updateFinalGradeAndFinalAttendance(@NonNull final StudentCourse studentCourse) {
        if(studentCourseMapper.updateFinalGradeAndFinalAttendance(studentCourse) != 1){
            log.error("Failed to update student course final grade: {}", studentCourse);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void updateNumOfCompletedPreCourse(@NonNull final Integer studentId, @NonNull final Integer classId) {
        studentCourseMapper.updateAndSelectByStudentIdAndClassId(studentId, classId);
    }

    @Override
    public void updateCourseStatusIfPreCoursesCompleted(@NonNull final Integer studentId) {
        studentCourseMapper.updateStatusByNumOfCompletedPreCourse(studentId);
    }

    @Override
    public List<StudentCourseVo> getStudentCourseStatus(@NonNull final Integer studentId) {
        return studentCourseMapper.selectByStudentId(studentId);
    }

    @Override
    public List<StudentIdAndCourseInfoVo> getAvailableCourses(@NonNull final Integer studentId) {
        return studentCourseMapper.getByStudentId(studentId);
    }

    @Override
    public boolean isCourseAvailable(@NonNull final Integer studentId, @NonNull final Integer courseId) {
        return studentCourseMapper.countByStudentIdAndCourseId(studentId, courseId) > 0;
    }

    @Override
    public void updateStudentCourseStatus(@NonNull final Integer studentId, @NonNull final Integer courseId, @NonNull final Integer status) {
        if(studentCourseMapper.updateByStudentIdAndCourseId(studentId, courseId, status) != 1){
            log.error("Failed to update student course status");
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }
}
