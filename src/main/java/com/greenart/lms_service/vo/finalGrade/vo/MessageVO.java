package com.greenart.lms_service.vo.finalGrade.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageVO {
    protected boolean status;
    protected String message;
    protected HttpStatus code;
}
