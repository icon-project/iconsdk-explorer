package com.dfg.icon.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class IconException extends CustomException{

    private String detail;

    public IconException(IconCode ucoinErrorCode) {
        super(ucoinErrorCode);
        detail = null;
    }

    public IconException(IconCode iconErrorCode, Throwable throwable) {
        super(iconErrorCode, throwable);
        this.detail = throwable.getMessage();
    }

    public IconException(IconCode iconErrorCode, String detail) {
        super(iconErrorCode, detail);
        this.detail = detail;
    }

    public HttpStatus getStatus() {
        return this.getCustomErrorCode().getStatus();
    }

    public String getDtail() {
        if(!StringUtils.isEmpty(this.detail)) {
            return this.detail;
        }
        return null;
    }
}
