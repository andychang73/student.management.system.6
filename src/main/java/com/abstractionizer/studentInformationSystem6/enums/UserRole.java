package com.abstractionizer.studentInformationSystem6.enums;

public enum UserRole {

    ADMIN(1),
    TEACHER(2),
    HEAD(3)
    ;

    private final Integer roleId;

    UserRole(Integer roleId){
        this.roleId = roleId;
    }

    public Integer getRoleId(){
        return this.roleId;
    }
}
