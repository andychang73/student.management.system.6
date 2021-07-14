package com.abstractionizer.studentInformationSystem6.enums;

import org.springframework.http.HttpStatus;

public interface BaseError {

    HttpStatus getHttpStatus();

    String getCode();

    String getMsg();
}
