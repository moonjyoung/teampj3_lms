package com.greenart.lms_service.vo.attend;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AttendResponseVO extends BasicResponse {
    @Schema(description = "학생 리스트")
    private List<AttendMasResponseVO> list;
}
