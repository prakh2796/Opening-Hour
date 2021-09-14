package com.example.wolt;

import com.example.wolt.enums.Day;
import com.example.wolt.utils.OpenHourUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class OpenHourUtilTests {

    @Test
    public void testConvertUnixTimeToDate() {
        assertEquals("10:00 AM", OpenHourUtil.convertUnixTimeToDate(36000));
    }

    @Test
    public void testLookupPreviousDay() {
        assertEquals(Day.WEDNESDAY.getDayOfWeek(), OpenHourUtil.lookupPreviousDay(Day.THURSDAY.getDayOfWeek()));
    }
}
