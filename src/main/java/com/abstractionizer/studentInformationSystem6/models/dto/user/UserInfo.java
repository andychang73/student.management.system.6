package com.abstractionizer.studentInformationSystem6.models.dto.user;

import com.abstractionizer.studentInformationSystem6.enums.UserGroup;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserInfo {
    private Integer id;
    private String username;
    private Date birthday;
    private String email;
    private String phone;
    private String address;
    private Integer reportTo;
    private Set<GrantedAuthority> authorities;
    private UserGroup userGroup;

    public UserInfo setUserGroup(UserGroup userGroup){
        this.userGroup = userGroup;
        return this;
    }
}
