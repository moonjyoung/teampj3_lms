package com.greenart.lms_service.vo.lecture;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseLectureTimeVO {
    @Schema(description = "요일정보(Integer)", example = "2")
    private Integer dowInt;
    @Schema(description = "요일정보(String)", example = "화")
    private String dwoStr;
    @Schema(description = "수업 시작시간", example = "3")
    private Integer start;
    @Schema(description = "수업 종료시간", example = "4")
    private Integer end;
}
