package com.greenart.lms_service.vo.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberLoginVO {
    @Schema(description = "아이디", example = "2023123001")
    private String id;
    @Schema(description = "비밀번호", example = "1234")
    private String pwd;
}
