package com.greenart.lms_service.vo.lecture;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LectureTimeVO {
    @Schema(description = "강의 명", example = "JAVA 백엔드 개발자 양성")
    private String title;
    @Schema(description = "강의 고유코드", example = "BAC001-00")
    private String type;
    @Schema(description = "강의 시간(시작)", example = "2023-02-28T11:00")
    private String startDate;
    @Schema(description = "강의 시간(끝)", example = "2023-02-28T12:50")
    private String endDate;
}
