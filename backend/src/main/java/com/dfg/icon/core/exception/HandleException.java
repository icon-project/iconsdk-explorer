package com.dfg.icon.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@ControllerAdvice
public class HandleException {

    private static final Logger logger = LoggerFactory.getLogger(HandleException.class);

    @ExceptionHandler(IconException.class)
    public ResponseEntity<ErrorMessageRes> handleUcoinException(IconException e) throws UnsupportedEncodingException {
        ErrorMessageRes res = new ErrorMessageRes(e.getCustomErrorCode(), e.getDtail());
        logger.error("IconException : {}", res.toString());
        logger.info("====================");

        return new ResponseEntity<>(res, e.getStatus());
    }
}
