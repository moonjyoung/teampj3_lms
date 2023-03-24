package com.greenart.lms_service.vo.lecture;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseLectureListVO {
    @Schema(description = "강의 번호", example = "1")
    private Long liSeq;
    @Schema(description = "강의 코드", example = "BAC001-01")
    private String liCode;
    @Schema(description = "강의 명", example = "JAVA 백엔드 개발자 양성")
    private String liName;
    @Schema(description = "강의실", example = "502")
    private String liClass;
    @Schema(description = "학점", example = "3")
    private Integer liGrade;
}
