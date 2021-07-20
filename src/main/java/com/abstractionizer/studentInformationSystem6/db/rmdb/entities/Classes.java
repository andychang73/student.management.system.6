package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("class")
public class Classes {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer courseId;

    private Integer staffId;

    private Integer semesterId;

    private Integer weekDay;

    private Time startTime;

    private Time endTime;

    private Integer maximum;

    private Integer version;

    private Integer status;

    private String creator;

    private Date createTime;

    private Date updateTime;
}
