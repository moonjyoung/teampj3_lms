package com.greenart.lms_service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.service.ScoreService;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.score.RequestScoreVO;
import com.greenart.lms_service.vo.score.ScoreResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "점수 정보 관리", description = "특정 강의의 학생 점수정보 조회/변경 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sco")
public class ScoreAPIController {
    private final ScoreService scoreService;

    @Operation(summary = "점수 정보 조회", description = "해당 강의의 모든 학생의 점수정보를 조회합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @GetMapping("/{liSeq}/{scSeq}")
    public ResponseEntity<ScoreResponseVO> getScore(
        @Parameter(description = "강의 번호", example = "1") @PathVariable Long liSeq, 
        @Parameter(description = "평가기준 번호(1 : 출석, 2 : 중간, 3 : 기말, 4 : 과제)", example = "2") @PathVariable Long scSeq
    ) {
        return new ResponseEntity<>(scoreService.getTestScore(liSeq, scSeq), HttpStatus.OK);
    }

    @PostMapping("/{liSeq}")
    public ResponseEntity<BasicResponse> postScore(
        @PathVariable Long liSeq,
        @RequestBody RequestScoreVO data
    ) {
        return new ResponseEntity<>(scoreService.postScore(liSeq, data), HttpStatus.OK);
    }
}
