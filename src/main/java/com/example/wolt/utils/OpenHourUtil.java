package com.example.wolt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class OpenHourUtil {

    /**
     *
     * @param unixValue unix value
     * @return Date in h:mm a format
     */
    public static String convertUnixTimeToDate(long unixValue) {
        Date date = new Date(unixValue * 1000L);

        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    /**
     *
     * @param day day of the week
     * @return previous day of the week
     */
    public static String lookupPreviousDay(String day){
        if(day.equalsIgnoreCase("monday")){
            return Constants.SUNDAY;
        }if(day.equalsIgnoreCase("tuesday")){
            return Constants.MONDAY;
        }if(day.equalsIgnoreCase("wednesday")){
            return Constants.TUESDAY;
        }if(day.equalsIgnoreCase("thursday")){
            return Constants.WEDNESDAY;
        }if(day.equalsIgnoreCase("friday")){
            return Constants.THURSDAY;
        }if(day.equalsIgnoreCase("saturday")){
            return Constants.FRIDAY;
        }if(day.equalsIgnoreCase("sunday")){
            return Constants.SATURDAY;
        }
        return "";
    }
}
