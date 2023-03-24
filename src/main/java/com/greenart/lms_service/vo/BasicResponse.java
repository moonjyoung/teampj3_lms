package com.greenart.lms_service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BasicResponse {
    @Schema(description = "성공/실패 여부", example = "true or false")
    protected Boolean status;
    @Schema(description = "메세지", example = "message")
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
