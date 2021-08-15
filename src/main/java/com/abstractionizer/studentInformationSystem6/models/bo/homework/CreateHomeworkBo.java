package com.abstractionizer.studentInformationSystem6.models.bo.homework;

import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class CreateHomeworkBo {

    @Min(value = 1, message = "minimum value is 1")
    private Integer classId;
    
    private Date deadLine;

    @Valid
    @NotEmpty(message = "must no be null nor empty")
    private List<QuestionAndAnswer> questionAndAnswers;
}
