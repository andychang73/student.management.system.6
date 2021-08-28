package com.abstractionizer.studentInformationSystem6.models.vo.studentCourse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseVo {
    private Integer year;
    private Integer semester;
    private Integer courseId;
    private String course;
    private Float grade;
    private Float attendance;
    private Integer status;
}
