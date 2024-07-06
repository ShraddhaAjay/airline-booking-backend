package com.booking.airline.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SeatNumberGenerator {

    private static Map<String,Integer> seatMap = new HashMap<>();
    public static String generateSeatNumber(LocalDateTime date, String planeId){
        String key = CustomDateTimeFormatter.getFormattedDate(date)+planeId;
        int seatNumber;
        if(seatMap.containsKey(key)){
            seatNumber = seatMap.get(key);
            if(seatNumber==50)
                return null;
            seatMap.put(key, ++seatNumber);
        } else{
            seatNumber = 1;
            seatMap.put(key, seatNumber);
        }
        return key+""+seatNumber;
    }
}
