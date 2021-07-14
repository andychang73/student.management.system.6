package com.abstractionizer.studentInformationSystem6.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseError{
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
