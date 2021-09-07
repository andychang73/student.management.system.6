package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Staff;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StaffMapper;
import com.abstractionizer.studentInformationSystem6.enums.AccountStatus;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.vo.user.teacher.TeacherVo;
import com.abstractionizer.studentInformationSystem6.sis.services.StaffService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffMapper staffMapper;

    @Override
    public Optional<Staff> getStaff(@NonNull final Integer id) {
        return Optional.ofNullable(staffMapper.selectByStaffId(id));
    }

    @Override
    public List<TeacherVo> selectByReportTo(@NonNull final Integer reportTo) {
        return staffMapper.selectByReportTo(reportTo);
    }

    @Override
    public Staff create(@NonNull final Staff staff) {
        if(staffMapper.insert(staff) != 1){
            log.error("Create staff failed: {}", staff);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return staff;
    }

    @Override
    public boolean isStaffExists(@NonNull final Integer id) {
        return staffMapper.countByStaffId(id) > 0;
    }

    @Override
    public boolean isTeacherWorkingForThisHeadOfCourse(Integer id, Integer reportTo) {
        return staffMapper.countByStaffIdAndReportTo(id, reportTo) > 0;
    }

    @Override
    public void freezeAccount(@NonNull final Integer userId) {
        if(staffMapper.updateStaff(userId, null, null, null, null, null, null, AccountStatus.FROZEN.getStatus()) != 1){
            log.error("Failed to freeze staff account, id: {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void firstTimeChangePassword(@NonNull final Integer userId, @NonNull final String password) {
        if(staffMapper.updateStaff(userId, password, null, null, null, null, AccountStatus.NOT_FIRST_LOGIN.getStatus(), null) != 1){
            log.error("Staff First time change password failed, id : {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void changePassword(@NonNull final Integer userId, @NonNull final String password) {
        if(staffMapper.updateStaff(userId, password, null, null, null, null, null, null) != 1){
            log.error("Staff Change password failed, id : {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void updateStaffInfo(@NonNull final Staff staff) {
        if(staffMapper.updateStaff(staff.getId(), null, null, staff.getEmail(), staff.getPhone(), staff.getAddress(), null, null) != 1){
            log.error("Update staff info failed, user: {}", staff);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }
}
