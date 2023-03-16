package com.greenart.lms_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.greenart.lms_service.vo.BasicResponse;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<BasicResponse> customException(CustomException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BasicResponse(e.getMessage()));
    }
}
