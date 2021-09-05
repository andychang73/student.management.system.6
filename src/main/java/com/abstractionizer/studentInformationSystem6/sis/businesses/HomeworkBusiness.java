package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.homework.CreateHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.SubmitHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.vo.homework.HomeworkVo;

import java.math.BigDecimal;
import java.util.List;


public interface HomeworkBusiness {

    void create(Integer staffId, CreateHomeworkBo bo);

    List<HomeworkVo> getValidHomeworks(Integer studentId, Integer classId);

    BigDecimal submitHomework(Integer studentId, SubmitHomeworkBo bo);
}
