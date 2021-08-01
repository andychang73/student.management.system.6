package com.abstractionizer.studentInformationSystem6.models.bo.user;

import lombok.Data;

@Data
public class CreateStaffBo extends CreateStudentBo {

    private Integer reportTo;
}
