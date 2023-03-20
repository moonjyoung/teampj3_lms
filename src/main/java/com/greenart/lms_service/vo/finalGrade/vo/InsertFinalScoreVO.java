package com.greenart.lms_service.vo.finalGrade.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertFinalScoreVO {
    @Schema(description = "학번")
    private String studentCode;
    @Schema(description = "부여할 성적, 수정 시 0 넣으면 성적 부여 취소")
    private Long grade;
}
