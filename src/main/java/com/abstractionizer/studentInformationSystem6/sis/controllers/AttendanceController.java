package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.bo.attendance.TakeAttendanceBo;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.AttendanceBusiness;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceBusiness attendanceBusiness;

    @PostMapping
    public SuccessResponse takeAttendance(@RequestAttribute("Staff")UserInfo userInfo,
                                          @RequestBody @Valid TakeAttendanceBo bo){
        attendanceBusiness.takeAttendance(userInfo.getUsername(), bo);
        return new SuccessResponse();
    }
}
