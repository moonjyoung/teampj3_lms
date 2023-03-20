package com.greenart.lms_service.vo.score;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScoreMasResponseVO {
    @Schema(description = "평가 번호", example = "1")
    private Long smasSeq;
    @Schema(description = "평가 이름", example = "중간시험")
    private String smasName;
    @Schema(description = "평가 일", example = "2023-01-01")
    private LocalDate smasDate;
    @Schema(description = "평가 만점(기본 설정 100)", example = "100")
    private Integer smasScore;
    @Schema(description = "학생별 평가 점수")
    private List<ScoreStuResponseVO> list;
}
