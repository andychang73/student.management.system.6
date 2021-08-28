package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Major;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.MajorCourse;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.major.CreateMajorBo;
import com.abstractionizer.studentInformationSystem6.models.vo.major.MajorVo;
import com.abstractionizer.studentInformationSystem6.models.vo.majorCourseVo.MajorCourseVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.MajorBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.CollegeService;
import com.abstractionizer.studentInformationSystem6.sis.services.CourseService;
import com.abstractionizer.studentInformationSystem6.sis.services.MajorCourseService;
import com.abstractionizer.studentInformationSystem6.sis.services.MajorService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MajorBusinessImpl implements MajorBusiness {

    private final CollegeService collegeService;
    private final MajorService majorService;
    private final CourseService courseService;
    private final MajorCourseService majorCourseService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(@NonNull final String creator, @NonNull final CreateMajorBo bo) {
        if(!collegeService.isCollegeIdExists(bo.getCollegeId())){
            throw new CustomExceptions(ErrorCode.COLLEGE_NON_EXISTS);
        }
        if(majorService.isMajorExists(bo.getMajor())){
            throw new CustomExceptions(ErrorCode.MAJOR_EXISTS);
        }
        if(!courseService.areCourseIdsExist(bo.getCourseIds())){
            throw new CustomExceptions(ErrorCode.COURSE_NON_EXISTS);
        }

        Major major = majorService.create(new Major().setCollegeId(bo.getCollegeId()).setMajor(bo.getMajor()).setCreator(creator));

        majorCourseService.create(generateMajorCourses(creator, major.getId(), bo.getCourseIds()));

    }

    @Override
    public List<MajorVo> getAllMajors() {
        return majorService.getAllMajors();
    }

    @Override
    public List<MajorCourseVo> getAllMajorsAndCourse() {
        return majorService.getAllMajorsAndCourses();
    }

    private Set<MajorCourse> generateMajorCourses(String creator, Integer majorId, Set<Integer> courseIds){
        return courseIds.stream()
                .map(c -> new MajorCourse().setMajorId(majorId).setCourseId(c).setCreator(creator))
                .collect(Collectors.toSet());
    }
}
