package com.booking.airline.util;

public class FairCalculator {

    private static final double SIMPLE_FAIR_PER_HUNDRED_KM = 1250D;
    public static double calculateSimpleFare(double speed, double distance){
        double fair = SIMPLE_FAIR_PER_HUNDRED_KM * distance/100;

        if(speed>=200)
            fair = fair * 1.05;
        else if(speed>=250)
            fair = fair * 1.07;
        else if(speed>=300)
            fair = fair * 1.1;
        else if(speed>=350)
            fair = fair * 1.15;
        else if(speed>=400)
            fair = fair * 1.17;
        return fair;
    }
}
