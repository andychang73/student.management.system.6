package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.classes.CreateClassBo;
import com.abstractionizer.studentInformationSystem6.models.dto.studentHomework.WeekNoAndGrade;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;

import java.util.List;

public interface ClassBusiness {

    void create(UserInfo userInfo, CreateClassBo bo);

    ClassesOfTheWeekVo getClassesOfTheWeek(Integer headId, Integer courseId);

    ClassInfoVo getClassInfoVo(Integer headId, Integer classId);

    List<ClassVo> getMyClassesOfThisSemester(Integer staffId);

    List<WeekNoAndGrade> getAllHomeWorkGradesOfTheClass(Integer studentId, Integer classId);
}
