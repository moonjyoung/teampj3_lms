package com.greenart.lms_service.api;

import com.greenart.lms_service.vo.finalGrade.vo.FinalScoreVO;
import com.greenart.lms_service.vo.finalGrade.vo.InsertFinalScoreVO;
import com.greenart.lms_service.vo.finalGrade.vo.MessageVO;
import com.greenart.lms_service.service.FinalScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/final")
@RequiredArgsConstructor
@Tag(name = "최종 성적 API", description = "최종 성적 CRU API")
public class FinalAPIController {
    private final FinalScoreService finalScoreService;

    @GetMapping("/{code}")
    @Operation(summary = "최종 성적 조회", description = "강의 코드 입력 시 해당 강의를 듣는 모든 학생들의 모든 성적을 조회합니다.")
    public ResponseEntity<List<FinalScoreVO>> getFinalScore(@Parameter(description = "강의 코드", example = "BAC001-00") @PathVariable String code) {
        return new ResponseEntity<>(finalScoreService.getFinalScore(code), HttpStatus.OK);
    }

    @PostMapping("/{code}")
    @Operation(summary = "최종 성적 입력", description = "강의 코드 와 학번 원하는 성적 입력 시 성적이 부여됩니다. 성적은 1(A)~13(F) 숫자값으로 부여")
    public ResponseEntity<MessageVO> insertFinalGrade(@Parameter(description = "강의 코드", example = "BAC001-00") @PathVariable String code, @RequestBody InsertFinalScoreVO data) {
        MessageVO response = finalScoreService.insertFinalScore(data, code);
        return new ResponseEntity<>(response, response.getCode());
    }

    @PatchMapping("/{code}")
    @Operation(summary = "최종 성적 수정", description = "강의 코드 와 학번 원하는 성적 입력 시 성적이 수정됩니다. 성적은 1(A)~13(F) 숫자값으로 부여, 0 입력 시 기존 입력 성적 취소")
    public ResponseEntity<MessageVO> updateFinalGrade(@Parameter(description = "강의 코드", example = "BAC001-00") @PathVariable String code, @RequestBody InsertFinalScoreVO data) {
        MessageVO response = finalScoreService.updateFinalScore(data, code);
        return new ResponseEntity<>(response, response.getCode());
    }
}
