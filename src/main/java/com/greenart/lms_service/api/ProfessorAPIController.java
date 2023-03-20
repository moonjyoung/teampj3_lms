package com.greenart.lms_service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.service.ProfessorService;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.lecture.LectureResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "교수-강의 정보 관리", description = "특정 교수님의 강의목록 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pro")
public class ProfessorAPIController {
    private final ProfessorService professorService;

    @Operation(summary = "교수-강의 정보 조회", description = "해당 교수님이 담당하는 모든 강의 목록을 조회합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @GetMapping("/{mbSeq}")
    public ResponseEntity<LectureResponseVO> getMyLecture(
        @Parameter(description = "교수 번호", example = "6") @PathVariable Long mbSeq
    ) {
        return new ResponseEntity<>(professorService.getMyLecture(mbSeq), HttpStatus.OK);
    }
}
