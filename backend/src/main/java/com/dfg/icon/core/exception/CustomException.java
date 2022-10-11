package com.dfg.icon.core.exception;


import lombok.Getter;

public class CustomException extends Exception {

    @Getter
    private CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getMessage());
        this.customErrorCode = customErrorCode;
    }

    public CustomException(CustomErrorCode customErrorCode, Throwable throwable){
        super(customErrorCode.getMessage(), throwable);
        this.customErrorCode = customErrorCode;
    }

    public CustomException(CustomErrorCode customErrorCode, String description){
        super(customErrorCode.getMessage(), new Throwable(description));
        this.customErrorCode = customErrorCode;
    }
}
