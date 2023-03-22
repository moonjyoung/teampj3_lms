package com.greenart.lms_service.vo.lecture;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LectureTimeResponseVO extends BasicResponse {
    @Schema(description = "강의 시간 리스트")
    private List<LectureTimeVO> list;
}
