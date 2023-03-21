package com.greenart.lms_service.vo.attend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AttendRequestVO {
    @Schema(description = "강의일 번호", example = "1")
    Long amasSeq;
    @Schema(description = "출석 상태 (0 : 결석, 1 : 출석)", example = "1")
    Integer status;
}
