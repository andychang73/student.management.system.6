package com.abstractionizer.studentInformationSystem6.models.vo.homework;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QuestionVo {
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
}
