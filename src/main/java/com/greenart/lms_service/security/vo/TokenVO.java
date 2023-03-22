package com.greenart.lms_service.security.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenVO {
    @Schema(description = "grantType", example = "Bearer")
    private String grantType;
    @Schema(description = "accessToken", example = "accessToken")
    private String accessToken;
    @Schema(description = "refreshToken", example = "refreshToken")
    private String refreshToken;
}
