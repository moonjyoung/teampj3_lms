package com.greenart.lms_service.vo;

import lombok.Data;

@Data
public class BasicResponse {
    private Boolean status;
    private String message;

    public BasicResponse() {}
    public BasicResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
    public BasicResponse(String message) {
        this.status = false;
        this.message = message;
    }
}
