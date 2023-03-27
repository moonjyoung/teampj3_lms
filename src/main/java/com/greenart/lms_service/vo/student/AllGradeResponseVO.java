package com.greenart.lms_service.vo.student;

import lombok.Data;

@Data
public class AllGradeResponseVO {
    private String code;
    private String name;
    private Integer grade;
    private String fgName;
    private Double fg3;
    private Double fg5;
}
