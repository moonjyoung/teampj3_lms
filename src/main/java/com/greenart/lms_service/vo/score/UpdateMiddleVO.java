package com.greenart.lms_service.vo.score;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Tag(name = "중간고사 만점수정")
@Data
public class UpdateMiddleVO {
    @Schema(description = "중간고사 만점수정")
    private Integer middleMax;
}
