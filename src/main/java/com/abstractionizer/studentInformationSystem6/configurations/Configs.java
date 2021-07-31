package com.abstractionizer.studentInformationSystem6.configurations;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.User;
import com.abstractionizer.studentInformationSystem6.enums.UserGroup;
import com.abstractionizer.studentInformationSystem6.sis.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Configs {

    @Bean
    public Map<UserGroup, UserService<? extends User>> userServices(List<UserService<? extends User>> users){
        return users.stream().collect(Collectors.toMap(UserService::getGroupId, user -> user));
    }
}
