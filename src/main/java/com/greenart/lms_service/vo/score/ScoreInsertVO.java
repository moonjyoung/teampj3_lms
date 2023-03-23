package com.greenart.lms_service.vo.score;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScoreInsertVO {
    @Schema(description = "평가 번호", example = "1")
    private Long masSeq;
    @Schema(description = "학생 번호", example = "1")
    private Long mbSeq;
    @Schema(description = "부여 점수", example = "50")
    private Integer score;
}
