package com.greenart.lms_service.vo.attend;

import java.util.List;

import lombok.Data;

@Data
public class AttendResponseVO {
    private String date;
    private List<AttendStuResponseVO> list;
}
