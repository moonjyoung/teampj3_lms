package com.greenart.lms_service.vo.lecture;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import lombok.Data;

@Data
public class LectureTimeResponseVO extends BasicResponse {
    private List<LectureTimeVO> list;
}
