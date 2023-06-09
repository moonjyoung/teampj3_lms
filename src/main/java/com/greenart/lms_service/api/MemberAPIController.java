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
import com.greenart.lms_service.service.StudentService;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.lecture.LectureTimeResponseVO;
import com.greenart.lms_service.vo.member.MemberLoginResponseVO;
import com.greenart.lms_service.vo.member.MemberLoginVO;
import com.greenart.lms_service.vo.student.StudentAllGradeResponseVO;

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
    private final StudentService studentService;

    @Operation(summary = "강의 시간표 조회", description = "학생/교수 회원의 강의시간 조회",
        responses = {
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @GetMapping("/timetable/{mbSeq}/{siSeq}")
    public ResponseEntity<LectureTimeResponseVO> getTimeTable(
        @Parameter(description = "회원(학생, 교수) 번호", example = "1") @PathVariable Long mbSeq,
        @Parameter(description = "학기 번호", example = "1") @PathVariable Long siSeq
    ) {
        return new ResponseEntity<>(memberService.getTimeTable(mbSeq, siSeq), HttpStatus.OK);
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

    @Operation(summary = "특정 학기에 받은 내 성적 조회", description = "학생이 한 학기동안 받은 성적 과목별 목록과 4.3/4.5 변환점수도 보여줍니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @GetMapping("/mygrade/{mbSeq}/{siSeq}")
    public ResponseEntity<StudentAllGradeResponseVO> getMyGrade(
        @Parameter(description = "학생 번호", example = "1") @PathVariable Long mbSeq,
        @Parameter(description = "학기 번호", example = "1") @PathVariable Long siSeq
    ) {
        return new ResponseEntity<>(studentService.getMyGrade(mbSeq, siSeq), HttpStatus.OK);
    }

}
