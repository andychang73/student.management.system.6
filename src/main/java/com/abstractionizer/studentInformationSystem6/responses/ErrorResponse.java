package com.abstractionizer.studentInformationSystem6.responses;

import com.abstractionizer.studentInformationSystem6.enums.BaseError;
import lombok.Data;

@Data
public class ErrorResponse<T> {
    private String code;
    private String msg;
    private T details;

    public ErrorResponse(String code, String msg, T details){
        this.code = code;
        this.msg = msg;
        this.details = details;
    }

    public ErrorResponse(BaseError baseError, T details){
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        this.details = details;
    }
}
