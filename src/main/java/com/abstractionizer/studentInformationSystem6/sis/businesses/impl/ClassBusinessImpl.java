package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Classes;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentClass;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.enums.UserRole;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.classes.CreateClassBo;
import com.abstractionizer.studentInformationSystem6.models.dto.studentHomework.WeekNoAndGrade;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassInfoVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassWithoutCourseVo;
import com.abstractionizer.studentInformationSystem6.models.vo.classes.ClassesOfTheWeekVo;
import com.abstractionizer.studentInformationSystem6.models.dto.semesterClass.ClassDateDto;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.ClassBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
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
public class ClassBusinessImpl implements ClassBusiness {

    private final SemesterClassService semesterClassService;
    private final StudentCourseService studentCourseService;
    private final StudentClassService studentClassService;
    private final StudentService studentService;
    private final DateConfigService dateConfigService;
    private final SemesterService semesterService;
    private final UserRoleService userRoleService;
    private final CourseService courseService;
    private final ClassService classService;
    private final StaffService staffService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(UserInfo userInfo, CreateClassBo bo) {
        Semester semester = semesterService.getCurrentSemester();

        if(!courseService.areCourseIdsExist(Set.of(bo.getCourseId()))){
            throw new CustomExceptions(ErrorCode.COURSE_NON_EXISTS);
        }
        if(!classService.isAllowedCreateClass(dateConfigService.getDate(), semester.getStartDate())){
            throw new CustomExceptions(ErrorCode.CLASS_CREATION_FORBIDDEN);
        }
        if(!userRoleService.isUserMatchingRole(bo.getTeacherId(), UserRole.TEACHER.getRoleId())){
            throw new CustomExceptions(ErrorCode.USER_ROLE_UNMATCHED, "This particular staff is not a teacher");
        }
        if(!staffService.isTeacherWorkingForThisHeadOfCourse(bo.getTeacherId(), userInfo.getId())){
            throw new CustomExceptions(ErrorCode.TEACHER_WRONG_COURSE);
        }
        if(classService.isTimeConflict(bo.getTeacherId(), bo.getWeekDay(), bo.getStartTime(), bo.getEndTime())){
            throw new CustomExceptions(ErrorCode.CLASS_CONFLICT);
        }

        Classes classes = new Classes()
                .setCourseId(bo.getCourseId())
                .setStaffId(bo.getTeacherId())
                .setSemesterId(semester.getId())
                .setWeekDay(bo.getWeekDay())
                .setStartTime(bo.getStartTime())
                .setEndTime(bo.getEndTime())
                .setMaximum(bo.getMaximum())
                .setCreator(userInfo.getUsername());

        Classes createdClass = classService.create(classes);

        List<SemesterClass> semesterClasses = getSemesterClasses(semesterService.getSemesterClassDates(semester.getStartDate(), bo.getWeekDay(), semester.getNumOfWeeks()), createdClass.getId());

        semesterClassService.create(semesterClasses);
    }

    @Override
    public ClassesOfTheWeekVo getClassesOfTheWeek(@NonNull final Integer headId, @NonNull final Integer courseId) {
        if(!courseService.isCourseExists(courseId)){
            throw new CustomExceptions(ErrorCode.COURSE_NON_EXISTS);
        }
        if(!courseService.isMyCourse(headId, courseId)){
            throw new CustomExceptions(ErrorCode.COURSE_INCORRECT_HEAD);
        }
        return classService.getClassesOfTheWeek(semesterService.getCurrentSemester().getId(),courseId);
    }

    @Override
    public ClassInfoVo getClassInfoVo(@NonNull final Integer headId, @NonNull final Integer classId) {
        if(!classService.isMyClass(headId, classId)){
            throw new CustomExceptions(ErrorCode.CLASS_NOT_FOUND, "This class does not belong to this head");
        }
        return classService.getClassInfoVo(classId);
    }

    @Override
    public List<ClassVo> getMyClassesOfThisSemester(@NonNull final Integer staffId) {
        return classService.getMyClassesOfThisSemester(staffId, semesterService.getCurrentSemester().getId());
    }

    @Override
    public List<WeekNoAndGrade> getAllHomeWorkGradesOfTheClass(@NonNull final Integer studentId, @NonNull final Integer classId) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }
        if(!classService.isClassValid(classId, semesterService.getCurrentSemester().getId())){
            throw new CustomExceptions(ErrorCode.CLASS_INVALID);
        }
        if(!studentClassService.isStudentInTheClass(classId, studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NOT_IN_CLASS);
        }

        return classService.getAllHomeworkGradesFromThisClass(studentId, classId);
    }

    @Override
    public List<ClassWithoutCourseVo> getAvailableClasses(@NonNull final Integer studentId, @NonNull final Integer courseId) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }
        if(!courseService.isCourseExists(courseId)){
            throw new CustomExceptions(ErrorCode.COURSE_NON_EXISTS);
        }
        if(!studentCourseService.isCourseAvailable(studentId, courseId)){
            throw new CustomExceptions(ErrorCode.COURSE_UNAVAILABLE);
        }

        Semester semester = semesterService.getCurrentSemester();
        if(semester.getEndDate().before(dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.INVALID_SEMESTER, "New semester has not yet been created, please contact admin");
        }
        if(!classService.isAllowedToEnroll(dateConfigService.getDate(), semester.getStartDate())){
            throw new CustomExceptions(ErrorCode.CLASS_ENROLLMENT_NOT_ALLOWED);
        }

        return classService.getAvailableClasses(courseId, semesterService.getCurrentSemester().getId());
    }


    private List<SemesterClass> getSemesterClasses(List<ClassDateDto> classDates, Integer classId){
        return classDates.stream()
                .map(d -> new SemesterClass()
                .setClassId(classId)
                .setWeekNo(d.getWeekNo())
                .setDate(d.getDate()))
                .collect(Collectors.toList());
    }
}
