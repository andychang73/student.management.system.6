package com.abstractionizer.studentInformationSystem6.models.vo.studentCourse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentIdAndCourseInfoVo {
    private Integer studentId;
    private Integer courseId;
    private String course;
}
