package com.abstractionizer.studentInformationSystem6.models.bo.homework;

import com.abstractionizer.studentInformationSystem6.annotations.ValidAnswers;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SubmitHomeworkBo {

    @Min(value = 0, message = "minimum value is 0")
    private Integer semesterClassId;

    @ValidAnswers(message = "the answer must either be a or b or c or d")
    @NotEmpty(message = "cannot be empty or null")
    private List<Character> answers;
}
