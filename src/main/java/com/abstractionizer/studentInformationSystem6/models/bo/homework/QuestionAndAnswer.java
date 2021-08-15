package com.abstractionizer.studentInformationSystem6.models.bo.homework;

import com.abstractionizer.studentInformationSystem6.annotations.ValidAnswer;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class QuestionAndAnswer {

    @NotEmpty(message = "must not be empty")
    private String question;

    @NotEmpty(message = "must not be empty")
    private String a;

    @NotEmpty(message = "must not be empty")
    private String b;

    @NotEmpty(message = "must not be empty")
    private String c;

    @NotEmpty(message = "must not be empty")
    private String d;

    @ValidAnswer(message = "must be a or b or c or d")
    @NotEmpty(message = "must not be empty")
    private String answer;

}
