package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("student")
public class Student extends BaseUser{

    public Student(){}

    public Student(String password, String username, Date birthday, String email, String phone, String address, String creator){
        super(password, username, birthday, email, phone, address, creator);
    }
}
