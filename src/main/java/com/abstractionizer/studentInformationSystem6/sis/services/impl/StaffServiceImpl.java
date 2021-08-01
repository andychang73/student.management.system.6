package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Staff;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StaffMapper;
import com.abstractionizer.studentInformationSystem6.enums.AccountStatus;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.StaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class StaffServiceImpl implements StaffService {

    private final StaffMapper staffMapper;

    @Override
    public Optional<Staff> getStaff(Integer id) {
        return Optional.ofNullable(staffMapper.selectByStaffId(id));
    }

    @Override
    public Staff create(Staff staff) {
        if(staffMapper.insert(staff) != 1){
            log.error("Create staff failed: {}", staff);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return staff;
    }

    @Override
    public boolean isStaffExists(Integer id) {
        return staffMapper.countByStaffId(id) > 0;
    }

    @Override
    public void freezeAccount(Integer userId) {
        if(staffMapper.updateStaff(userId, null, null, null, null, null, null, AccountStatus.FROZEN.getStatus()) != 1){
            log.error("Failed to freeze staff account, id: {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void firstTimeChangePassword(Integer userId, String password) {
        if(staffMapper.updateStaff(userId, password, null, null, null, null, AccountStatus.NOT_FIRST_LOGIN.getStatus(), null) != 1){
            log.error("First time change password failed, id : {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void changePassword(Integer userId, String password) {
        if(staffMapper.updateStaff(userId, password, null, null, null, null, null, null) != 1){
            log.error("Change password failed, id : {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void updateStaffInfo(Staff staff) {
        if(staffMapper.updateStaff(staff.getId(), null, null, staff.getEmail(), staff.getPhone(), staff.getAddress(), null, null) != 1){
            log.error("Update staff info failed, user: {}", staff);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }
}
