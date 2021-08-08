package com.abstractionizer.studentInformationSystem6.models.dto.semesterClass;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ClassDateDto {
    private Integer weekNo;
    private Date date;
}
