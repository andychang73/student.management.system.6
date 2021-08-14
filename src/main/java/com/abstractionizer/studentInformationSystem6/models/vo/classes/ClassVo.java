package com.abstractionizer.studentInformationSystem6.models.vo.classes;

import lombok.Data;

import java.sql.Time;

@Data
public class ClassVo {
    private Integer classId;
    private String course;
    private Integer weekDay;
    private Time startTime;
    private Time endTime;
}
