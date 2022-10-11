package com.dfg.icon.core.exception;

import org.springframework.http.HttpStatus;

public interface CustomErrorCode {
    public HttpStatus getStatus();
    public String getCode();
    public String getMessage();
}
