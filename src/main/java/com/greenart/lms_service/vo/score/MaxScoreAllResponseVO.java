package com.greenart.lms_service.vo.score;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MaxScoreAllResponseVO {
    @Schema(description = "강의 번호", example = "1")
    private Long liSeq;
    @Schema(description = "강의 명", example = "JAVA 백엔드 개발자 양성")
    private String name;
    @Schema(description = "강의 코드", example = "BAC001-00")
    private String code;
    @Schema(description = "평가 방식(상대평가/절대평가)", example = "상대평가")
    private String evaluation;
    @Schema(description = "평가기준 리스트")
    private List<MaxScoreListResponseVO> list;
}
