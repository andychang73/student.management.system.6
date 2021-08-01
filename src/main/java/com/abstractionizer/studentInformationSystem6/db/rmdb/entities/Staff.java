package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.Set;


@Data
@Accessors(chain = true)
@TableName("staff")
public class Staff extends User {

    private Integer reportTo;

    public Staff(){}

    public Staff(Integer id, String email, String phone, String address) {
        super(id, email, phone, address);
    }

    public Staff(String password, String username, Date birthday, String email, String phone, String address, Integer reportTo, String creator){
        super(password, username, birthday, email, phone, address, creator);
        this.reportTo = reportTo;
    }
}
