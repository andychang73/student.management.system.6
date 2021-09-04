package com.abstractionizer.studentInformationSystem6.models.vo.homework;

import com.abstractionizer.studentInformationSystem6.models.bo.homework.QuestionAndAnswer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Map;

@Data
@Accessors(chain = true)
public class HomeworkDto2 {

    @JsonProperty("semesterClassId")
    private Integer semesterClassId;

    @JsonProperty("homework")
    private Map<Integer, QuestionAndAnswer> questionAndAnswers;
}
