package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Course;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.CourseMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Override
    public Course create(Course course) {
        if(courseMapper.insert(course) != 1){
            log.error("Create course failed: {}", course);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return course;
    }

    @Override
    public boolean isCourseExists(String course) {
        return courseMapper.countByCourseIdsOrCourse(null, course) > 0;
    }

    @Override
    public boolean areCourseIdsExist(Set<Integer> courseIds) {
        return courseMapper.countByCourseIdsOrCourse(courseIds, null) == courseIds.size();
    }
}
