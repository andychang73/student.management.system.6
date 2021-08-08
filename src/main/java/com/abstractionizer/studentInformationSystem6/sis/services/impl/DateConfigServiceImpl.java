package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.DateConfigMapper;
import com.abstractionizer.studentInformationSystem6.sis.services.DateConfigService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Objects;


@Slf4j
@Service
@AllArgsConstructor
public class DateConfigServiceImpl implements DateConfigService {

    private final DateConfigMapper dateConfigMapper;

    @Override
    public Date getDate() {
        String dateStr = dateConfigMapper.getDateConfig();
        if(Objects.nonNull(dateStr) && !dateStr.isEmpty()){
            JSONObject json = JSON.parseObject(dateStr);
            if(json.containsKey("date") && Objects.nonNull(json.get("date"))){
                return json.getDate("date");
            }
        }
        return new Date();
    }
}
