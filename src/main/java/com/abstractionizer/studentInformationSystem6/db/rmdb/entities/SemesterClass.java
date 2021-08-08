package com.abstractionizer.studentInformationSystem6.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("semester_class")
public class SemesterClass {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer classId;

    private Integer weekNo;

    private Date date;

    private Date createTime;

    private Date updateTime;
}
