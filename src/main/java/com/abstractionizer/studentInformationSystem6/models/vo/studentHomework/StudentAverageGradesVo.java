package com.abstractionizer.studentInformationSystem6.models.vo.studentHomework;

import lombok.Data;

@Data
public class StudentAverageGradesVo {
    private Integer studentId;
    private String studentName;
    private Float averageGrade;
}
