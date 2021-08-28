package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Staff;
import com.abstractionizer.studentInformationSystem6.models.vo.user.teacher.TeacherVo;

import java.util.List;
import java.util.Optional;


public interface StaffService extends UserService{

    Optional<Staff> getStaff(Integer id);

    List<TeacherVo> selectByReportTo(Integer reportTo);

    Staff create(Staff staff);

    boolean isStaffExists(Integer id);

    boolean isTeacherWorkingForThisHeadOfCourse(Integer id, Integer reportTo);

    void updateStaffInfo(Staff staff);
}
