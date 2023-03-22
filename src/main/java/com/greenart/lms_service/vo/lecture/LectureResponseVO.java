package com.greenart.lms_service.vo.lecture;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LectureResponseVO extends BasicResponse {
    @Schema(description = "강의 목록")
    private List<ResponseLectureListVO> list;
}
