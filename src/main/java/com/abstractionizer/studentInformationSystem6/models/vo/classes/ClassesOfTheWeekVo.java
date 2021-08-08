package com.abstractionizer.studentInformationSystem6.models.vo.classes;

import com.abstractionizer.studentInformationSystem6.models.dto.classes.ClassDto;
import lombok.Data;

import java.util.List;

@Data
public class ClassesOfTheWeekVo {
    private Integer courseId;
    private String course;
    private List<ClassDto> classDto;
}
