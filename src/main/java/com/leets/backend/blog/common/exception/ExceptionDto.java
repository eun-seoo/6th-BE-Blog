package com.leets.backend.blog.common.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"code", "message", "errorMsg"})
public class ExceoptionDto {

    private final Integer code;
    private final String message;
    private final String errorMsg;

    public ExceoptionDto(ErrorCode errorCode, String errorMsg) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errorMsg = errorMsg;
    }

    public static ExceoptionDto of(ErrorCode errorCode, String errorMsg) {
        return new ExceoptionDto(errorCode, errorMsg);
    }

    public Integer getCode() { return code; }
    public String getMessage() { return message; }
    public String getErrorMsg() { return errorMsg; }
}
