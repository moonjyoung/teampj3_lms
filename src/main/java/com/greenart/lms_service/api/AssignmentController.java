package com.greenart.lms_service.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.service.AssignmentService;
import com.greenart.lms_service.vo.AssignmentResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "과제 조회", description = "강의별 과제 조회API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;
    @Operation(summary = "해당 강의 과제리스트 조회", description = "해당 강의의 과제를 리스트로 조회합니다.")
    @GetMapping("/{liSeq}")
        public ResponseEntity<AssignmentResponseVO> listAssignment (@PathVariable Long liSeq){
            return new ResponseEntity<>(assignmentService.liAList(liSeq),HttpStatus.OK);
        }
    
}

