package com.abstractionizer.studentInformationSystem6.db.rmdb.mappers;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Course;
import com.abstractionizer.studentInformationSystem6.models.vo.course.CourseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface CourseMapper extends BaseMapper<Course> {

    int countByCourseIdsOrCourseOrHeadId(@Param("course_ids")Set<Integer> courseIds, @Param("course") String course, @Param("head_id") Integer headId);

    List<CourseVo> getAllCoursesOrByHeadId(@Param("head_id") Integer headId);
}
