package com.greenart.lms_service.vo.attend;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AttendStuResponseVO {
    @Schema(description = "강의일 번호", example = "1")
    private Long amasSeq;
    @Schema(description = "강의 일", example = "2021-02-28")
    private LocalDate date;
    @Schema(description = "출결(미입력 : (공백), 출석 : O, 결석 : X)", example = "X")
    private String status;
}
