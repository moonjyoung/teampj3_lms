package com.greenart.lms_service.vo.attend;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AttendAllDayRequestVO {
    LocalDate date;
    Integer status;
}
