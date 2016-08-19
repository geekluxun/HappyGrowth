package com.geekluxun.www.happygrowth.util;

import java.util.Calendar;

/**
 * Created by mark on 2016/8/16.
 */
public class DateTimeUtils {

    public static final String DateFormat1 = "yyyy年MM月dd日";
    public static final String DateFormat2 = "yyyy-MM-dd";

    private static final Calendar mCalendar = Calendar.getInstance();

    public static int getHour(){
        int hour;
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static int getMinute(){
        int minute;
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        minute = mCalendar.get(Calendar.MINUTE);
        return minute;
    }

    public static int getYear(){
        int year;
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        year = mCalendar.get(Calendar.YEAR);
        return year;
    }

    public static int getMonth(){
        int month;
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        month = mCalendar.get(Calendar.MONTH);
        return month;
    }

    public static int getDay(){
        int day;
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }


}
