package com.abstractionizer.studentInformationSystem6.models.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDto {
    private String token;
    private UserInfo userInfo;
}
