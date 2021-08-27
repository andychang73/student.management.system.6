package com.abstractionizer.studentInformationSystem6.models.vo.studentHomework;

import com.abstractionizer.studentInformationSystem6.models.dto.studentHomework.WeekNoAndGrade;
import lombok.Data;

import java.util.List;

@Data
public class StudentWeeklyHomeworkGradeVo {
    private Integer studentId;
    private String studentName;
    private List<WeekNoAndGrade> grades;
}
