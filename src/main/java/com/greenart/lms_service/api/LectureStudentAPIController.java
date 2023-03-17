package com.greenart.lms_service.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.service.LectureStudentService;
import com.greenart.lms_service.vo.LectureStudentListVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/{proSeq}/lec")
// http://localhost:8888/api/{proSeq}/lec/{liSeq}/stu-list
@RequiredArgsConstructor
public class LectureStudentAPIController {
    private final LectureStudentService lecStuService;

    @GetMapping("/{liSeq}/stu-list")
    public ResponseEntity<List<LectureStudentListVO>> getlectStuList(@PathVariable Long liSeq) {
        return new ResponseEntity<List<LectureStudentListVO>>(lecStuService.lectureStudentList(liSeq), HttpStatus.OK);
    }
}
