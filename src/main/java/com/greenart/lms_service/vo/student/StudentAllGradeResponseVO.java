package com.greenart.lms_service.vo.student;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import lombok.Data;

@Data
public class StudentAllGradeResponseVO extends BasicResponse {
    private Long mbSeq;
    private String name;
    private String id;
    private String major;
    private Integer grade;
    private Double total3;
    private Double total5;
    private List<AllGradeResponseVO> list;
}
