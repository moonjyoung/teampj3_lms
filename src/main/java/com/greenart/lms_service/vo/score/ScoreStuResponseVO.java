package com.greenart.lms_service.vo.score;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScoreStuResponseVO {
    @Schema(description = "학생별 평가 번호", example = "1")
    private Long sstuSeq;
    @Schema(description = "학생 번호", example = "1")
    private Long mbSeq;
    @Schema(description = "학생 이름", example = "강백호")
    private String name;
    @Schema(description = "점수", example = "90")
    private Double score;
}
