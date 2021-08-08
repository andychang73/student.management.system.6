package com.abstractionizer.studentInformationSystem6.models.vo.classes;

import com.abstractionizer.studentInformationSystem6.models.vo.user.student.StudentAttendanceAndGrade;
import lombok.Data;

import java.util.List;

@Data
public class ClassInfoVo {
    private String teacherName;
    List<StudentAttendanceAndGrade> students;
}
