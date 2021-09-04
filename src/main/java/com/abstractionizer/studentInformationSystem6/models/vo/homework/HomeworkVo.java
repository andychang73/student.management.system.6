package com.abstractionizer.studentInformationSystem6.models.vo.homework;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HomeworkVo {
    private Integer semesterClassId;
    private JSONObject questions;
}
