package com.greenart.lms_service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.service.MemberService;
import com.greenart.lms_service.vo.lecture.LectureTimeResponseVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberAPIController {
    private final MemberService memberService;

    @GetMapping("/timetable/{mbSeq}")
    public ResponseEntity<LectureTimeResponseVO> getTimeTable(@PathVariable Long mbSeq) {
        return new ResponseEntity<>(memberService.getTimeTable(mbSeq), HttpStatus.OK);
    }
}
