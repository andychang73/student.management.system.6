package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Student;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.StudentMapper;
import com.abstractionizer.studentInformationSystem6.enums.AccountStatus;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    @Override
    public Student create(@NonNull final Student student) {
        if(studentMapper.insert(student) != 1){
            log.error("Failed to create student: {}", student);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return student;
    }

    @Override
    public Optional<Student> getStudent(@NonNull final Integer studentId) {
        Student student = studentMapper.selectByStudentId(studentId);
        if(Objects.isNull(student)){
            return Optional.empty();
        }

        student.setAuthorities(new HashSet<>(Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"))));
        return Optional.of(student);
    }

    @Override
    public boolean isStudentIdExists(@NonNull final Integer studentId) {
        return studentMapper.countByStudentIds(Set.of(studentId)) > 0;
    }

    @Override
    public boolean areStudentIdsExist(@NonNull final Set<Integer> studentIds) {
        if(studentIds.isEmpty()){
            return false;
        }
        return studentMapper.countByStudentIds(studentIds) == studentIds.size();
    }

    @Override
    public void updateStudentInfo(@NonNull final Student student) {
        if(studentMapper.updateStudent(student.getId(), null, null, student.getEmail(), student.getPhone(), student.getAddress(), null, null) != 1){
            log.error("Update staff info failed, user: {}", student);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void freezeAccount(@NonNull final Integer userId) {
        if(studentMapper.updateStudent(userId, null, null, null, null, null, null, AccountStatus.FROZEN.getStatus()) != 1){
            log.error("Failed to freeze student account, id: {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void firstTimeChangePassword(@NonNull final Integer userId, @NonNull final String password) {
        if(studentMapper.updateStudent(userId, password, null, null, null, null, AccountStatus.NOT_FIRST_LOGIN.getStatus(), null) != 1){
            log.error("Student First time change password failed, id : {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void changePassword(@NonNull final Integer userId, @NonNull final String password) {
        if(studentMapper.updateStudent(userId, password, null, null, null, null, null, null) != 1){
            log.error("Student Change password failed, id : {}", userId);
            throw new CustomExceptions(ErrorCode.DATA_UPDATE_FAILED);
        }
    }
}
