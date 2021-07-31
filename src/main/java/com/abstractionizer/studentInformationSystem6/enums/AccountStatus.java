package com.abstractionizer.studentInformationSystem6.enums;

public enum AccountStatus {

    FROZEN(0),
    NORMAL(1),
    FIRST_LOGIN(1),
    NOT_FIRST_LOGIN(0);

    private final Integer status;

    AccountStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }
}
