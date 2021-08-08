package com.abstractionizer.studentInformationSystem6.models.dto.classes;

import lombok.Data;

import java.sql.Time;

@Data
public class ClassDto {
    private Integer classId;
    private Integer weekDay;
    private Integer teacherId;
    private String teacherName;
    private Time startTime;
    private Time endTime;
}
