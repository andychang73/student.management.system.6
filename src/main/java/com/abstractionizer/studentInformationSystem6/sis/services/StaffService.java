package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Staff;

import java.util.Optional;


public interface StaffService {

    Optional<Staff> getStaff(Integer id);

    Staff create(Staff staff);

    boolean isStaffExists(Integer id);

    void freezeAccount(Integer userId);

    void firstTimeChangePassword(Integer userId, String password);

    void changePassword(Integer userId, String password);

    void updateStaffInfo(Staff staff);
}
