package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("homework")
public class Homework {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer semesterClassId;

    private JSONObject homework;

    private Date deadline;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
