package com.abstractionizer.studentInformationSystem6.exceptions;

import com.abstractionizer.studentInformationSystem6.enums.BaseError;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomExceptions extends RuntimeException{
    private HttpStatus httpStatus;
    private String code;
    private String msg;
    private String details;

    public CustomExceptions(BaseError baseError){
        this(baseError, null);
    }

    public CustomExceptions(BaseError baseError, String details){
        this.httpStatus = baseError.getHttpStatus();
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        this.details = details;
    }
}
