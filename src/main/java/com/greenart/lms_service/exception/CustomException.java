package com.greenart.lms_service.exception;

public class CustomException extends RuntimeException {
    public CustomException() {
        super("필수 입력값을 잘못 입력하였습니다.");
    }
    public CustomException(String message) {
        super(message);
    }
}
