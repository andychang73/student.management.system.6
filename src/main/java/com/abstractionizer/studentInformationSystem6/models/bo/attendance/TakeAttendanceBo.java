package com.abstractionizer.studentInformationSystem6.models.bo.attendance;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class TakeAttendanceBo {

    @Min(value = 1)
    private Integer classId;

    @NotEmpty
    private Set<Integer> studentIds;
}
