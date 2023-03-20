package com.greenart.lms_service.utils;

public class ConvertDayOfWeek {
    public static String convertDayOfWeek(Integer dayOfWeek) {
        String dowStr = "";

        if (dayOfWeek==1) dowStr = "월";
        else if (dayOfWeek==2) dowStr = "화";
        else if (dayOfWeek==3) dowStr = "수";
        else if (dayOfWeek==4) dowStr = "목";
        else if (dayOfWeek==5) dowStr = "금";
        else if (dayOfWeek==6) dowStr = "토";
        else if (dayOfWeek==7) dowStr = "일";

        return dowStr;
    }
}
