package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@TableName("student")
public class Student extends User {

    public Student(){}

    public Student(Integer id, String email, String phone, String address) {
        super(id, email, phone, address);
    }

    public Student(String password, String username, Date birthday, String email, String phone, String address, String creator){
        super(password, username, birthday, email, phone, address, creator);
    }
}
