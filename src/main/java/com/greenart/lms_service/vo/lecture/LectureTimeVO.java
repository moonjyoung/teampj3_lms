package com.greenart.lms_service.vo.lecture;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LectureTimeVO {
    private String title;
    private String type;
    private String startDate;
    private String endDate;
}
