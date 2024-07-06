package com.booking.airline.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormatter {

    public static DateTimeFormatter FORMAT_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddhhmmss");
    public static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyMMdd");
    public static String getFormattedYearDate(){
        return LocalDateTime.now().format(formatter);
    }
    public static String getFormattedDate(LocalDateTime date){
        return date.format(formatter2);
    }
}
