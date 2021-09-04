package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.abstractionizer.studentInformationSystem6.models.vo.studentCourse.StudentCourseVo;

import java.util.List;

public interface StudentCourseService {

    void create(List<StudentCourse> studentCourses);

    void updateFinalGradeAndFinalAttendance(StudentCourse studentCourse);

    void updateNumOfCompletedPreCourse(Integer studentId, Integer classId);

    void updateCourseStatusIfPreCoursesCompleted(Integer studentId);

    List<StudentCourseVo> getStudentCourseStatus(Integer studentId);
}
