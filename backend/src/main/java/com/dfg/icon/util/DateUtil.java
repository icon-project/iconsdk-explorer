package com.dfg.icon.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {

    public static final String getFormattedDateString(Date date, String format, Locale locale, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    public static final String getFormattedDateString(Date date, String format) {
        return getFormattedDateString(date, format, Locale.getDefault(), TimeZone.getDefault());
    }

    public static final String getFormattedDateStringByNow(String format) {
        return getFormattedDateString(getNowDate(), format, Locale.getDefault(), TimeZone.getDefault());
    }
    public static Date getFormattedDate(String checkTime, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format); //"yyyyMMddHHmm"; //checkTime Format
        return sdf.parse(checkTime);
    }

    public static Date getNowDate() {
        String pattern = "yyyyMMddHHmmss";
        String now = getFormattedDateString(new Date(), pattern);
        try {
            return getFormattedDate(now, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Calendar getNowCalendar() {
        Calendar cal  = Calendar.getInstance(Locale.getDefault());
        cal.setTime(getNowDate());
        return cal;
    }
    public static Date getNextDateWithDateType(Date date, int fieldType, int nextValue) {
        Calendar cal = getNowCalendar();
        cal.setTime(date);
        cal.set(fieldType, cal.get(fieldType) + nextValue);
        return cal.getTime();
    }

    public static int getWeekVal(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    // 현재 날짜포함 14일치의 날짜 리스트를 반환
    public static List<String> getChartDailyList() {
        List<String> res = new ArrayList<>();
        Calendar cal = getNowCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        for(int i=0; i<14; i++) {
            String yyyyMMdd = String.format("%s-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
            res.add(yyyyMMdd);
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        return res;
    }
}
