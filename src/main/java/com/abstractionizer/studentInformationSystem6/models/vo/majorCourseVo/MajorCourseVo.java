package com.abstractionizer.studentInformationSystem6.models.vo.majorCourseVo;

import com.abstractionizer.studentInformationSystem6.models.vo.course.CourseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorCourseVo {
    private Integer id;
    private String major;
    private List<CourseVo> courses;
}
