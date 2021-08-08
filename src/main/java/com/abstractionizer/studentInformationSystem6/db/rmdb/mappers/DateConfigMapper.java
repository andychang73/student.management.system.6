package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DateConfigMapper {

    String getDateConfig();
}
