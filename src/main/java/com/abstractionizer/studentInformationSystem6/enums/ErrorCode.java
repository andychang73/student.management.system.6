package com.abstractionizer.studentInformationSystem6.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseError{

    INVALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, "10000", "Invalid method arguments"),
    INVALID_HEADERS(HttpStatus.BAD_REQUEST, "10001", "Missing servlet request part"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "10002", "Invalid credentials"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "10003", "Invalid token"),
    DATA_INSERT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "10004", "Data insert failed"),
    DATA_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "10005", "Data update failed"),
    DATA_DELETION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "10006", "Failed to delete data"),

    USER_LOGGED_IN(HttpStatus.INTERNAL_SERVER_ERROR, "10011", "This account has already been logged in"),
    USER_FIRST_LOGIN(HttpStatus.INTERNAL_SERVER_ERROR, "10012", "This is your first login, please change the password"),
    USER_NOT_FIRST_LOGIN(HttpStatus.INTERNAL_SERVER_ERROR, "10013", "This is not your first login"),
    USER_NON_EXISTS(HttpStatus.BAD_REQUEST, "10014", "User not found"),
    USER_ROLE_UNMATCHED(HttpStatus.BAD_REQUEST, "10015", "User does not match role"),

    ACCOUNT_FROZEN(HttpStatus.UNAUTHORIZED, "10020", "Your account has been frozen, please contact admin"),

    STUDENT_NON_EXISTS(HttpStatus.BAD_REQUEST, "10030", "Student does not exist"),
    STUDENT_MAJOR_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "10031", "Student has already selected this major"),

    TEACHER_WRONG_COURSE(HttpStatus.BAD_REQUEST, "10041", "This teacher does not teach this subject"),

    ROLE_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "10100", "Role already exists"),
    ROLE_AUTHORITY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "10101", "Authorities has been bound to this role"),
    ROLE_NOT_EXISTS(HttpStatus.BAD_REQUEST, "10102", "This role does not exist"),

    AUTHORITY_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "10200", "Authority not found"),

    COLLEGE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "10050", "College already exists"),
    COLLEGE_NON_EXISTS(HttpStatus.BAD_REQUEST, "10051", "College does not exists"),

    MAJOR_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "10060", "Major already exist"),
    MAJOR_NON_EXISTS(HttpStatus.BAD_REQUEST, "10061", "Major does not exists"),

    COURSE_EXISTS(HttpStatus.BAD_REQUEST, "10070", "Course already exists"),
    COURSE_NON_EXISTS(HttpStatus.BAD_REQUEST, "10071", "Course doesn't exists"),
    COURSE_INCORRECT_HEAD(HttpStatus.FORBIDDEN, "10072", "This course does not belong to this head"),
    CLASS_NOT_FOUND(HttpStatus.FORBIDDEN, "10073", "This class does not belong to this head"),
    CLASS_NON_EXISTS(HttpStatus.NOT_FOUND, "10074", "This class id does not exist"),

    SEMESTER_EXISTS(HttpStatus.BAD_REQUEST, "10092", "Semester has been created"),
    SEMESTER_NON_EXISTS(HttpStatus.BAD_REQUEST, "10093", "Semester does not exist"),
    SEMESTER_NOT_ENDED(HttpStatus.INTERNAL_SERVER_ERROR, "10094", "Not allowed to create new semester unless current semester ends"),

    CLASS_CREATION_FORBIDDEN(HttpStatus.FORBIDDEN, "10200", "Class is only allow to be created 15 days to 30 days before semester starts"),
    CLASS_CONFLICT(HttpStatus.INTERNAL_SERVER_ERROR, "10201", "Class conflict"),
    CLASS_STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "10202", "Students do not belong to this class"),

    ASSESSED(HttpStatus.INTERNAL_SERVER_ERROR, "10301", "This teacher has been assessed"),
    ASSESSMENT_FORBIDDEN(HttpStatus.FORBIDDEN, "10302", "Not allowed to assess before current semester ends"),

    ATTENDANCE_TAKEN(HttpStatus.CONFLICT, "10400", "This week's attendance has been taken"),
    ATTENDANCE_FORBIDDEN(HttpStatus.FORBIDDEN, "10401", "Attendance can only be taken after the class is ended")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String msg;

    ErrorCode(HttpStatus httpStatus, String code, String msg){
        this.httpStatus = httpStatus;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
