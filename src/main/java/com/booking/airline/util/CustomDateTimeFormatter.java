package com.booking.airline.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormatter {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddhhmmss");
    public static String getFormattedYearDate(){
        return LocalDateTime.now().format(formatter);
    }
}
