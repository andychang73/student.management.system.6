package com.abstractionizer.studentInformationSystem6.models.vo.studentClass;

import com.abstractionizer.studentInformationSystem6.models.vo.semesterClass.SemesterClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.user.student.StudentVo;
import lombok.Data;

import java.util.List;

@Data
public class StudentsOfTheClass {
    private Integer semesterClassId;
    private Integer weekNo;
    private List<StudentVo> students;
}
