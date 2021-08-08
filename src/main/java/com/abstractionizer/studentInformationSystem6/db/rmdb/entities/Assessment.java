package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

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
@TableName("assessment")
public class Assessment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer semesterId;

    private Integer staffId;

    private Float result;

    private String assessedBy;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}
