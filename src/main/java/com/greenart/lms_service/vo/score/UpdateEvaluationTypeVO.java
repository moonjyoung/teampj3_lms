package com.greenart.lms_service.vo.score;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Tag(name = "평가방식 수정", description = "")
@Data
public class UpdateEvaluationTypeVO {
    @Schema(description = "평가방식(1 상대 / 2 절대 / 3 P)")
    private Integer liEvaluationType;
}
