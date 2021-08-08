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
@TableName("student_class")
public class StudentClass {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer studentId;

    private Integer classId;

    private Float homeworkAverageGrade;

    private Float attendance;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
