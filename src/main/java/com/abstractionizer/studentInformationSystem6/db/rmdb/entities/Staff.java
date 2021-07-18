package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("staff")
public class Staff extends BaseUser{

    private Integer reportTo;

    public Staff(){}

    public Staff(String password, String username, Date birthday, String email, String phone, String address, String creator, Integer reportTo){
        super(password, username, birthday, email, phone, address, creator);
        this.reportTo = reportTo;
    }
}
