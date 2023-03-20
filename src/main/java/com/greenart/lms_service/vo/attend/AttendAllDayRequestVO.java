package com.greenart.lms_service.vo.attend;

import java.time.LocalDate;

import com.greenart.lms_service.vo.BasicResponse;

import lombok.Data;

@Data
public class AttendAllDayRequestVO extends BasicResponse {
    LocalDate date;
    Integer aStatus;
}
