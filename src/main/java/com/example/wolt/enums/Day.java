package com.example.wolt.enums;

import java.util.HashMap;
import java.util.Map;

public enum Day {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6);

    private int dayOfWeek;

    Day(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    private static Map<Integer, Day> map = new HashMap<>();

    static {
        for(Day day : Day.values())
            map.put(day.dayOfWeek, day);
    }

    public static Day valueOf(int dayOfWeek) {
        return map.get(dayOfWeek);
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
