package com.greenart.lms_service.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.greenart.lms_service.exception.CustomException;

public class ConvertClassDateTime {
    public static LocalDateTime convertClassDateTime(LocalDate date, Integer timeInt) {
        LocalTime time = null;

        if (timeInt==1) time = LocalTime.of(9, 0, 0);
        else if (timeInt==2) time = LocalTime.of(10, 0, 0);
        else if (timeInt==3) time = LocalTime.of(11, 0, 0);
        else if (timeInt==4) time = LocalTime.of(12, 0, 0);
        else if (timeInt==5) time = LocalTime.of(13, 0, 0);
        else if (timeInt==6) time = LocalTime.of(14, 0, 0);
        else if (timeInt==7) time = LocalTime.of(15, 0, 0);
        else if (timeInt==8) time = LocalTime.of(16, 0, 0);
        else if (timeInt==9) time = LocalTime.of(17, 0, 0);
        else if (timeInt==10) time = LocalTime.of(18, 0, 0);
        else throw new CustomException("시간을 확인해주세요.");
        
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        return dateTime;
    }
}
