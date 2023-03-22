package com.greenart.lms_service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.service.MemberService;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.lecture.LectureTimeResponseVO;
import com.greenart.lms_service.vo.member.MemberLoginResponseVO;
import com.greenart.lms_service.vo.member.MemberLoginVO;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원 마이페이지", description = "회원(학생, 교수, 행정)이 공통으로 사용하는 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberAPIController {
    private final MemberService memberService;

    @Operation(summary = "강의 시간표 조회", description = "학생/교수 회원의 강의시간 조회",
        responses = {
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @GetMapping("/timetable/{mbSeq}")
    public ResponseEntity<LectureTimeResponseVO> getTimeTable(
        @Parameter(description = "회원(학생, 교수) 번호", example = "6") @PathVariable Long mbSeq
    ) {
        return new ResponseEntity<>(memberService.getTimeTable(mbSeq), HttpStatus.OK);
    }

    @Operation(summary = "로그인", description = "학생/교수/행정 계정이 모두 공통 api를 사용하며 로그인합니다.",
    responses = {
        @ApiResponse(responseCode = "200", description = "true"),
        @ApiResponse(responseCode = "400", description = "false",
            content = @Content(schema = @Schema(implementation = BasicResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseVO> postLogin(
        @Parameter(description = "로그인 데이터(id/pwd)") @RequestBody MemberLoginVO login
    ) {
        return new ResponseEntity<>(memberService.postLogin(login), HttpStatus.OK);
    }

}
