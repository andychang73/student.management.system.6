package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("student_homework")
public class StudentHomework {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer homeworkId;

    private Integer studentId;

    private String answers;

    private BigDecimal grade;

    private Date createTime;

    private Date updateTime;
}
