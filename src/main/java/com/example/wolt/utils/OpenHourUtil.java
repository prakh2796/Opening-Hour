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
     * @param day day of the week(Day starts from MONDAY(0))
     * @return previous day of the week. If day is 0 then the previous day is 6.
     */
    public static int lookupPreviousDay(int day) {
        if(day == 0)
            return 6;
        return day-1;
    }
}
