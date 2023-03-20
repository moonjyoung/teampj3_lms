package com.greenart.lms_service.vo.attend;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AttendResponseVO extends BasicResponse {
    @Schema(description = "학생 번호", example = "1")
    private Long mbSeq;
    @Schema(description = "학생 이름", example = "강백호")
    private String name;
    @Schema(description = "출석일 정보 리스트")
    private List<AttendStuResponseVO> list;
}
