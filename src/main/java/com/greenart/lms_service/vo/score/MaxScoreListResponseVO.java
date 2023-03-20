package com.greenart.lms_service.vo.score;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MaxScoreListResponseVO {
    @Schema(description = "평가기준 번호", example = "1")
    private Long ssSeq;
    @Schema(description = "평가기준 이름", example = "중간시험")
    private String cateName;
    @Schema(description = "만점", example = "100")
    private Integer scoreMax;
}
