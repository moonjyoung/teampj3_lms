package com.greenart.lms_service.vo.score;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MaxScoreBasicResponseVO extends BasicResponse {
    @Schema(description = "강의 리스트")
    private List<MaxScoreAllResponseVO> list;
}
