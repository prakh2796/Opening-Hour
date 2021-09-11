package com.example.wolt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class OpenHourUtil {
    public static String convertUnixTimeToDate(long unixValue) {
        Date date = new Date(unixValue * 1000L);

        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    public static String lookupPreviousDay(String day){
        if(day.equalsIgnoreCase("monday")){
            return "Sunday";
        }if(day.equalsIgnoreCase("tuesday")){
            return "Monday";
        }if(day.equalsIgnoreCase("wednesday")){
            return "Tuesday";
        }if(day.equalsIgnoreCase("thursday")){
            return "Wednesday";
        }if(day.equalsIgnoreCase("friday")){
            return "Thursday";
        }if(day.equalsIgnoreCase("saturday")){
            return "Friday";
        }if(day.equalsIgnoreCase("sunday")){
            return "Saturday";
        }
        return "";
    }
}
