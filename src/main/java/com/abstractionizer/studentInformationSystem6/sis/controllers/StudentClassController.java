package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.studentClass.EnrollBo;
import com.abstractionizer.studentInformationSystem6.models.dto.attendance.AttendanceDto;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentClass.StudentsOfTheClass;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentClassBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/studentClass")
public class StudentClassController {

    private final StudentClassBusiness studentClassBusiness;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/{id}")
    public SuccessResponse<StudentsOfTheClass> getStudentsOfTheClass(@RequestAttribute("Staff")UserInfo userInfo,
                                                                     @PathVariable("id") Integer classId){
        return new SuccessResponse<>(studentClassBusiness.getStudentsOfTheClass(userInfo.getId(), classId));
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/attendance/{classId}")
    public SuccessResponse<List<AttendanceDto>> getStudentsCurrentAttendance(@RequestAttribute("Staff") UserInfo userInfo,
                                                                             @PathVariable("classId") Integer classId){
        return new SuccessResponse<>(studentClassBusiness.getStudentsCurrentAttendance(userInfo.getId(), classId));
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping
    public SuccessResponse<List<ClassVo>> getScheduleOfThisSemester(@RequestAttribute("Student") UserInfo userInfo){
        return new SuccessResponse<>(studentClassBusiness.getClassScheduleOfThisSemester(userInfo.getId()));
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping
    public SuccessResponse enroll(@RequestAttribute("Student") UserInfo userInfo,
                                  @RequestBody @Valid EnrollBo bo){
        studentClassBusiness.enroll(userInfo.getId(), bo);
        return new SuccessResponse();
    }
}
