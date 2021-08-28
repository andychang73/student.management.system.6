package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.user.*;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.user.SuccessfulLoginVo;
import com.abstractionizer.studentInformationSystem6.models.vo.user.teacher.TeacherVo;

import java.util.List;

public interface StaffBusiness extends UserBusiness{

    SuccessfulLoginVo login(UserLoginBo bo);

    List<TeacherVo> getMyTeachers(Integer headId);

    void createStaff(String creator, CreateStaffBo bo);
}
