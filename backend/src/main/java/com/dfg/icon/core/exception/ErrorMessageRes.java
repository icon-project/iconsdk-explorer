package com.dfg.icon.core.exception;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class ErrorMessageRes {
    String errCode;
    String message;
    String detail;

    public ErrorMessageRes(CustomErrorCode customErrorCode) {
        this.errCode = customErrorCode.getCode();
        this.message = customErrorCode.getMessage();
    }

    public ErrorMessageRes(CustomErrorCode customErrorCode, String detail) {
        this.errCode = customErrorCode.getCode();
        this.message = customErrorCode.getMessage();
        this.detail = detail;
    }

    public ErrorMessageRes(){}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
