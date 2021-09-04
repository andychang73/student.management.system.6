package com.abstractionizer.studentInformationSystem6.models.bo.homework;

import com.abstractionizer.studentInformationSystem6.annotations.ValidAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Answer {

    @ValidAnswer(message = "must be a or b or c or d")
    @NotEmpty(message = "must not be empty")
    private String answer;
}
