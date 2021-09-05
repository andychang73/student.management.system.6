package com.abstractionizer.studentInformationSystem6.models.vo.classes;

import lombok.Data;
import java.sql.Time;

@Data
public class ClassWithoutCourseVo {
    private Integer id;
    private Integer weekDay;
    private Time startTime;
    private Time endTime;
    private Integer maximum;
    private Integer numOfStudentEnrolled;
}
