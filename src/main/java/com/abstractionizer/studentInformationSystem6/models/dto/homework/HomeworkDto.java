package com.abstractionizer.studentInformationSystem6.models.dto.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkDto {
    private Integer semesterClassId;
    private String questions;
}
