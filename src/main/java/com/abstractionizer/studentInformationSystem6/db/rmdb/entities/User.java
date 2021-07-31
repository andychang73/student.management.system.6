package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.abstractionizer.studentInformationSystem6.utils.MD5Util;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
public class User {

    @TableId(type = IdType.AUTO)
    protected Integer id;
    protected String password;
    protected String username;
    protected Date birthday;
    protected String email;
    protected String phone;
    protected String address;
    protected Integer firstTimeLogin;
    protected Integer status;
    protected String creator;
    protected Date createTime;
    protected Date updateTime;

    @Transient
    protected Set<GrantedAuthority> authorities;

    public User(){}

    public User(Integer id, String email, String phone, String address) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public User(String password, String username, Date birthday, String email, String phone, String address, String creator) {
        this.password = password;
        this.username = username;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.creator = creator;
    }

    public User(String password, String username, Date birthday, String email, String phone, String address, String creator, Set<GrantedAuthority> authorities) {
        this.password = password;
        this.username = username;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.creator = creator;
        this.authorities = authorities;
    }

    protected static String generateDefaultPassword(Date birthday){
        StringBuilder sb = new StringBuilder();
        List.of(new SimpleDateFormat("yyyy-MM-dd").format(birthday).split("-")).forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.md5("19110101"));
    }
}
