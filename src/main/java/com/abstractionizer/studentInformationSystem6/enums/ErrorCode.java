package com.abstractionizer.studentInformationSystem6.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseError{

    INVALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, "10000", "Invalid method arguments"),
    INVALID_HEADERS(HttpStatus.BAD_REQUEST, "10001", "Missing servlet request part")
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
