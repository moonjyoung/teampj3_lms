package com.greenart.lms_service.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.AttendInfoStudentEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.service.AttendService;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.attend.AttendAllDayRequestVO;
import com.greenart.lms_service.vo.attend.AttendResponseVO;
import com.greenart.lms_service.vo.attend.AttendStuResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "출결 정보 관리", description = "특정 강의의 학생 출결정보 조회/변경 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/atd")
public class AttendAPIController {
    private final AttendService attendService;

    @Operation(summary = "출결 정보 조회", description = "해당 강의의 모든 수업일/학생 출결정보를 조회합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false",
                content = @Content(schema = @Schema(implementation = BasicResponse.class)))
        })
    @GetMapping("/{liSeq}")
    public ResponseEntity< List<AttendResponseVO> > getAttendDay(
        @Parameter(description = "강의 번호", example = "1") @PathVariable Long liSeq
    ) {
        return new ResponseEntity<>(attendService.getAttendDay(liSeq), HttpStatus.OK);
    }

    @PostMapping("/{liSeq}")
    public ResponseEntity<BasicResponse> postAllAttend(
        @PathVariable Long liSeq,
        @RequestBody AttendAllDayRequestVO data
    ) {
        Integer status = data.getAStatus();
        if (status!=null && status!=1 && status!=0) throw new CustomException("유효하지 않은 상태값입니다.(0:결석, 1:출석)");
        if (data.getDate()==null) throw new CustomException("강의일을 확인해주세요.");

        return new ResponseEntity<>(attendService.patchAttendAll(liSeq, data), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{liSeq}/asdf")
    public ResponseEntity<BasicResponse> patchAttend(
        @PathVariable Long liSeq
    ) {
        BasicResponse response = new BasicResponse(true, "변경 성공");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
