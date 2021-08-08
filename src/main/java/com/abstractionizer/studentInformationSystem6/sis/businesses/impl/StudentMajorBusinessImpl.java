package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentCourse;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentMajor;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.studentMajor.CreateStudentMajorBo;
import com.abstractionizer.studentInformationSystem6.models.dto.course.PreCourseCountDto;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentMajorBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class StudentMajorBusinessImpl implements StudentMajorBusiness {

    private final StudentCourseService studentCourseService;
    private final StudentMajorService studentMajorService;
    private final StudentService studentService;
    private final MajorService majorService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(CreateStudentMajorBo bo) {
        if(!studentService.isStudentIdExists(bo.getStudentId())){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }
        if(!majorService.areMajorIdsExist(Set.of(bo.getMajorId()))){
            throw new CustomExceptions(ErrorCode.MAJOR_NON_EXISTS);
        }
        if(studentMajorService.isStudentMajorExists(bo.getStudentId(), bo.getMajorId())){
            throw new CustomExceptions(ErrorCode.STUDENT_MAJOR_EXISTS);
        }

        studentMajorService.create(new StudentMajor().setMajorId(bo.getMajorId()).setStudentId(bo.getStudentId()));

        List<StudentCourse> studentCourses = getStudentCourse(majorService.getPreCourseCount(bo.getMajorId()), bo.getStudentId());

        log.info("[StudentMajorBusinessImpl][Create] student courses: {}", JSONObject.toJSON(studentCourses));

        studentCourseService.create(studentCourses);

    }

    private List<StudentCourse> getStudentCourse(List<PreCourseCountDto> dto, Integer studentId){
        return dto.stream()
                .map(d -> new StudentCourse()
                .setStudentId(studentId)
                .setCourseId(d.getCourseId())
                .setNumOfPreCourse(d.getNumOfPreCourse()))
                .collect(Collectors.toList());
    }
}
