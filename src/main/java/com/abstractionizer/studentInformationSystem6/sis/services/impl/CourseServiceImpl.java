package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Course;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.CourseMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.dto.course.PreCourseCountDto;
import com.abstractionizer.studentInformationSystem6.models.vo.course.CourseVo;
import com.abstractionizer.studentInformationSystem6.sis.services.CourseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Override
    public Course create(@NonNull final Course course) {
        if(courseMapper.insert(course) != 1){
            log.error("Create course failed: {}", course);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return course;
    }

    @Override
    public List<CourseVo> getAllCourses() {
        return courseMapper.getAllCoursesOrByHeadId(null);
    }

    @Override
    public List<CourseVo> getCoursesByHeadId(@NonNull final Integer headId) {
        return courseMapper.getAllCoursesOrByHeadId(headId);
    }

    @Override
    public boolean isCourseExists(@NonNull final String course) {
        return courseMapper.countByCourseIdsOrCourseOrHeadId(null, course, null) > 0;
    }

    @Override
    public boolean isCourseExists(Integer courseId) {
        return courseMapper.countByCourseIdsOrCourseOrHeadId(Set.of(courseId), null, null) > 0;
    }

    @Override
    public boolean areCourseIdsExist(@NonNull final Set<Integer> courseIds) {
        if(courseIds.isEmpty()){
            return false;
        }
        return courseMapper.countByCourseIdsOrCourseOrHeadId(courseIds, null, null) == courseIds.size();
    }

    @Override
    public boolean isMyCourse(@NonNull final Integer headId, @NonNull final Integer courseId) {
        return courseMapper.countByCourseIdsOrCourseOrHeadId(Set.of(courseId),null, headId) > 0;
    }
}
