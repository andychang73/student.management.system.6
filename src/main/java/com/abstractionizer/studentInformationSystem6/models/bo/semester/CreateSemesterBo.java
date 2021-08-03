package com.abstractionizer.studentInformationSystem6.models.bo.semester;

import com.abstractionizer.studentInformationSystem6.annotations.ValidSemesterStartDate;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
public class CreateSemesterBo {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidSemesterStartDate(message = "must not be past and has to be Monday")
    private Date startDate;

    @Range(min = 1 , max = 2, message = "can only be 1 or 2")
    private Integer semester;

    @Min(value = 16, message = "must be more than 16 weeks")
    private Integer numOfWeeks;
}
