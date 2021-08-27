package com.abstractionizer.studentInformationSystem6.sis.controllers;

import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentAverageGradesVo;
import com.abstractionizer.studentInformationSystem6.models.vo.studentHomework.StudentWeeklyHomeworkGradeVo;
import com.abstractionizer.studentInformationSystem6.responses.SuccessResponse;
import com.abstractionizer.studentInformationSystem6.sis.businesses.StudentHomeworkBusiness;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/studentHomework")
public class StudentHomeworkController {

    private final StudentHomeworkBusiness studentHomeworkBusiness;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/{classId}")
    public SuccessResponse<List<StudentWeeklyHomeworkGradeVo>> getStudentWeeklyHomeworkGrades(@RequestAttribute("Staff") UserInfo userInfo,
                                                                                              @PathVariable("classId") Integer classId){
        return new SuccessResponse<>(studentHomeworkBusiness.getAllHomeworkGrades(userInfo.getId(), classId));
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/averageGrade/{classId}")
    public SuccessResponse<List<StudentAverageGradesVo>> getStudentAverageGrades(@RequestAttribute("Staff") UserInfo userInfo,
                                                                                 @PathVariable("classId") Integer classId){
        return new SuccessResponse<>(studentHomeworkBusiness.getStudentAverageGrades(userInfo.getId(), classId));
    }
}
