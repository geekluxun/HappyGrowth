package com.geekluxun.www.happygrowth.util;

import java.util.Calendar;

/**
 * Created by mark on 2016/8/16.
 */
public class DateTimeUtils {

    private static final Calendar mCalendar = Calendar.getInstance();

    public static int getHour(){
        int hour;
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        hour = mCalendar.get(Calendar.HOUR);
        return hour;
    }

    public static int getMinute(){
        int minute;
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        minute = mCalendar.get(Calendar.MINUTE);
        return minute;
    }


}
