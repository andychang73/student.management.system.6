package com.abstractionizer.studentInformationSystem6.models.bo.homework;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class Question {

    @JsonProperty("question")
    @NotEmpty(message = "must not be empty")
    private String question;

    @JsonProperty("a")
    @NotEmpty(message = "must not be empty")
    private String a;

    @JsonProperty("b")
    @NotEmpty(message = "must not be empty")
    private String b;

    @JsonProperty("c")
    @NotEmpty(message = "must not be empty")
    private String c;

    @JsonProperty("d")
    @NotEmpty(message = "must not be empty")
    private String d;
}
