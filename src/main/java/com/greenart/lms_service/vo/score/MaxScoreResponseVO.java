package com.greenart.lms_service.vo.score;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MaxScoreResponseVO extends BasicResponse {
    @Schema(description = "강의 번호", example = "1")
    private Long liSeq;
    @Schema(description = "강의 명", example = "JAVA 백엔드 개발자 양성")
    private String name;
    @Schema(description = "평가기준 리스트")
    private List<MaxScoreListResponseVO> list;
}