package com.greenart.lms_service.vo;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentListResponseVO {
    @Schema(description = "과제 번호", example = "1")
    private Long aSeq; 
    @Schema(description = "과제 이름", example = "과제1")
    private String aName; 
    @Schema(description = "과제 날짜", example = "2023-06-01")
    private String aDate; 
}
