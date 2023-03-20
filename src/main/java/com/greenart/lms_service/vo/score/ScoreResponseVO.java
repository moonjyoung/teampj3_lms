package com.greenart.lms_service.vo.score;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScoreResponseVO extends BasicResponse {
    @Schema(description = "점수 정보 리스트")
    private List<ScoreMasResponseVO> list;
}
