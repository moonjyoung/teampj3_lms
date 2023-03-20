package com.greenart.lms_service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.service.StaffService;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.score.MaxScoreResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "교무 행정 관리페이지", description = "교무행정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stf")
public class StaffAPIController {
    private final StaffService staffService;

    @Operation(summary = "강의별 성적기준 정보 조회", description = "해당 강의의 성적기준(만점) 정보를 조회합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @GetMapping("/{liSeq}")
    public ResponseEntity<MaxScoreResponseVO> getLecMaxScore(
        @Parameter(description = "강의 번호", example = "1") @PathVariable Long liSeq
    ) {
        return new ResponseEntity<>(staffService.getLectureScoreMax(liSeq), HttpStatus.OK);
    }
}
