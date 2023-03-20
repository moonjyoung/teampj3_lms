package com.greenart.lms_service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BasicResponse {
    protected Boolean status;
    protected String message;

    public BasicResponse() {
        this.status = true;
        this.message = "데이터 없음";
    }
    public BasicResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
    public BasicResponse(String message) {
        this.status = false;
        this.message = message;
    }
}
