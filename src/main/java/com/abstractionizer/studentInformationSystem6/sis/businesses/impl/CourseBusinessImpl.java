package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Course;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.PreCourse;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.enums.UserRole;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.course.CreateCourseBo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.CourseBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.CourseService;
import com.abstractionizer.studentInformationSystem6.sis.services.PreCourseService;
import com.abstractionizer.studentInformationSystem6.sis.services.UserRoleService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CourseBusinessImpl implements CourseBusiness {

    private final CourseService courseService;
    private final PreCourseService preCourseService;
    private final UserRoleService userRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(@NonNull final String creator, @NonNull final CreateCourseBo bo) {
        if(courseService.isCourseExists(bo.getCourse())){
            throw new CustomExceptions(ErrorCode.COURSE_EXISTS);
        }
        if(!userRoleService.areUserRoleExist(bo.getHeadId(), Set.of(UserRole.HEAD.getRoleId()))){
            throw new CustomExceptions(ErrorCode.USER_ROLE_UNMATCHED);
        }

        Course course = courseService.create(new Course().setCourse(bo.getCourse()).setHeadId(bo.getHeadId()).setCreator(creator));

        if(Objects.isNull(bo.getPreCourses()) || bo.getPreCourses().isEmpty()){
            return;
        }

        if(!courseService.areCourseIdsExist(bo.getPreCourses())){
            throw new CustomExceptions(ErrorCode.COURSE_NON_EXISTS);
        }

        preCourseService.create(generatePreCourses(course.getId(), bo.getPreCourses(), creator));
    }

    private Set<PreCourse> generatePreCourses(Integer courseId, Set<Integer> preCourseIds, String creator){
        return preCourseIds.stream()
                .map(c -> new PreCourse().setCourseId(courseId).setPreCourseId(c).setCreator(creator))
                .collect(Collectors.toSet());
    }
}
