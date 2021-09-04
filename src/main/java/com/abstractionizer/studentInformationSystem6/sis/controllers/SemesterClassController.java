package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.attendance.AttendanceVo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.SemesterClassBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/semesterClass")
public class SemesterClassController {

    private final SemesterClassBusiness semesterClassBusiness;

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/{id}")
    public SuccessResponse<List<AttendanceVo>> getAttendanceOfTheClass(@RequestAttribute("Student")UserInfo userInfo,
                                                                       @PathVariable("id") Integer classId){
        return new SuccessResponse<>(semesterClassBusiness.getClassAttendance(userInfo.getId(), classId));
    }
}
