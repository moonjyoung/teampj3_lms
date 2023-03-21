package com.greenart.lms_service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.service.AttendService;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.attend.AttendRequestVO;
import com.greenart.lms_service.vo.attend.AttendResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "출결 정보 관리", description = "특정 강의의 학생 출결정보 조회/변경 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/atd")
public class AttendAPIController {
    private final AttendService attendService;

    @Operation(summary = "강의의 전체 출결 정보 조회", description = "해당 강의의 모든 수업일/학생 출결정보를 조회합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @GetMapping("/{liSeq}")
    public ResponseEntity<AttendResponseVO> getAttendDay(
        @Parameter(description = "강의 번호", example = "1") @PathVariable Long liSeq
    ) {
        return new ResponseEntity<>(attendService.getAttendDay(liSeq), HttpStatus.OK);
    }

    @Operation(summary = "전체 출결 정보 수정", description = "특정 수업일의 모든 학생의 출결정보를 수정합니다.",
        responses = {
            @ApiResponse(responseCode = "202", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @PostMapping("/{liSeq}")
    public ResponseEntity<BasicResponse> postAttendAll(
        @Parameter(description = "강의 번호", example = "1") @PathVariable Long liSeq,
        @Parameter(description = "출석 정보 변경 RequestVO") @RequestBody AttendRequestVO data
    ) {
        Integer status = data.getStatus();
        if (status==null || !(status==1 || status==0)) throw new CustomException("유효하지 않은 상태값입니다.(0:결석, 1:출석)");

        return new ResponseEntity<>(attendService.postAttendAll(liSeq, data), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "학생 출결 정보 수정", description = "특정 수업일의 학생의 출결정보를 수정합니다.",
        responses = {
            @ApiResponse(responseCode = "202", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @PostMapping("/{liSeq}/{mbSeq}")
    public ResponseEntity<BasicResponse> postAttendAll(
        @Parameter(description = "강의 번호", example = "1") @PathVariable Long liSeq,
        @Parameter(description = "출석 정보 변경 RequestVO") @RequestBody AttendRequestVO data,
        @Parameter(description = "학생 번호", example = "1") @PathVariable Long mbSeq
    ) {
        Integer status = data.getStatus();
        if (status==null || !(status==1 || status==0)) throw new CustomException("유효하지 않은 상태값입니다.(0:결석, 1:출석)");
        
        return new ResponseEntity<>(attendService.postAttend(liSeq, data, mbSeq), HttpStatus.ACCEPTED);
    }
}
