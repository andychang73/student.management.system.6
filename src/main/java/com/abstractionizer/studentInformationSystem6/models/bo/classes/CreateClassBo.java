package com.abstractionizer.studentInformationSystem6.models.bo.classes;

import com.abstractionizer.studentInformationSystem6.annotations.ValidTime;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.sql.Time;

@Data
public class CreateClassBo {

    @Min(value = 1, message = "must enter course id")
    private Integer courseId;

    @Range(min = 2, max = 6, message = "week day must be between 2 and 6")
    private Integer weekDay;

    @ValidTime(message = "invalid format")
    private Time startTime;

    @ValidTime(message = "invalid format")
    private Time endTime;

    @Min(value = 1, message = "must enter teacher id")
    private Integer teacherId;

    @Min(value = 1, message = "must enter maximum number of student")
    private Integer maximum;
}
