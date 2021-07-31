package com.abstractionizer.studentInformationSystem6.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseError{

    INVALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, "10000", "Invalid method arguments"),
    INVALID_HEADERS(HttpStatus.BAD_REQUEST, "10001", "Missing servlet request part"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "10002", "Invalid credentials"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "10003", "Invalid token"),

    USER_LOGGED_IN(HttpStatus.INTERNAL_SERVER_ERROR, "10011", "This account has already been logged in"),
    USER_FIRST_LOGIN(HttpStatus.INTERNAL_SERVER_ERROR, "10012", "This is your first login, please change the password"),
    USER_NOT_FIRST_LOGIN(HttpStatus.INTERNAL_SERVER_ERROR, "10013", "This is not your first login"),
    USER_NON_EXISTS(HttpStatus.BAD_REQUEST, "10014", "User not found"),

    DATA_INSERT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "10000", "Data insert failed"),
    DATA_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "10001", "Data update failed"),
    DATA_DELETION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "10002", "Failed to delete data"),

    ACCOUNT_FROZEN(HttpStatus.UNAUTHORIZED, "10020", "Your account has been frozen, please contact admin"),

    ROLE_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "10100", "Role already exists"),
    ROLE_AUTHORITY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "10101", "Authorities has been bound to this role"),
    ROLE_NOT_EXISTS(HttpStatus.BAD_REQUEST, "10102", "This role does not exist"),

    AUTHORITY_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "10200", "Authority not found")
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
