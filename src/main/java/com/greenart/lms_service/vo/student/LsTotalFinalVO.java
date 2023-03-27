package com.greenart.lms_service.vo.student;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import lombok.Data;

@Data
public class LsTotalFinalVO extends BasicResponse {
    private LectureStudentTotalInfoVO data;
}
