package fun.peri.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chenq on 2017/10/12.
 */
public class DateUtil {
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


    public static void main(String[] args) throws Exception {
        Date dateStart = DateUtil.strToDate("2016-07-01 22:20:26");
        int count = getSubmitRedeemDayCount(dateStart);
        System.out.println("已经提交转出申请天数：" + count);

    }

    public static int getSubmitRedeemDayCount(Date dateStart) throws Exception {
        Date dateEnd = new Date();
        int distance = (int) getDaysOfTwoDate(dateStart, dateEnd);

        List<Date> dateList = new ArrayList<Date>();

        for (int i = 0; i < distance; i++) {
            Date dateTemp = addDay(dateStart, i);

            boolean isWeekend = isWeekend(dateTemp);

            if (isWeekend == false) {
                dateList.add(dateTemp);
//                System.out.println(formatDate(dateTemp, "yyyy-MM-dd HH:mm"));
            }

        }

        return dateList.size();
    }

    public static boolean isWeekend(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day = cal.get(Calendar.DAY_OF_WEEK);

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDaysOfTwoDate(Date before, Date after) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long beforeTime = sdf.parse(sdf.format(before)).getTime();
        long afterTime = sdf.parse(sdf.format(after)).getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyyMMdd）
     */
    public static String getDate1() {
        return getDate("yyyyMMdd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date, String pattern) {
        return formatDate(date, pattern);
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 减少天数
     *
     * @param date 日期
     * @return 返回相减后的日期
     */
    public static Date reduceDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }


    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 判断两天日期是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return false;
        }
        if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
            return false;
        }
        if (cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) {
            return false;
        }
        return true;
    }

    /**
     * 判断两天日期是否为同一天
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @param date2
     * @return
     */
    public static boolean isSameDate(String dateStr, Date date2) {

        Date date1 = null;
        try {
            date1 = DateUtil.strToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date1 == null) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return false;
        }
        if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
            return false;
        }
        if (cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) {
            return false;
        }
        return true;
    }

    /**
     * 日期增加天数
     *
     * @param nowDate
     * @param dayNum
     * @return
     */
    public static Date addDay(Date nowDate, int dayNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(nowDate);
        calendar.add(calendar.DATE, dayNum);
        return calendar.getTime();
    }

    /**
     * 日期增加月份
     *
     * @param ““
     * @param monthNum
     * @return
     */
    public static Date addMonth(Date nowDate, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.MONTH, monthNum);
        return calendar.getTime();
    }

    /**
     * 字符串转换date，日期格式：yyyy-MM-dd
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr) throws ParseException {
        return strToDate(dateStr, parsePatterns[0]);
    }

    public static Date strToDate(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//小写的mm表示的是分钟
        return sdf.parse(dateStr);
    }

    /**
     * 获取当前时间后几天*
     */
    public static Date getDateAfterSomeDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 获取当前0点时间
     *
     * @return
     */
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当前0点时间
     *
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当前23:59:59秒时间
     *
     * @return
     */
    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }


    /**
     * 获取当前00:00:00秒时间
     *
     * @return
     */
    public static Date getBeginTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 00);
        todayEnd.set(Calendar.MINUTE, 00);
        todayEnd.set(Calendar.SECOND, 00);
        todayEnd.set(Calendar.MILLISECOND, 000);
        return todayEnd.getTime();
    }


    /**
     * 获取当前23:59:59秒时间
     *
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 得到指定日期后一天的0时
     *
     * @param date
     * @return
     */
    public static Date getNextDay000000(Date date) {
        return addDay(getYYYYMMDD000000(date), 1);
    }

    /**
     * 得到指定日期前一天的24时
     *
     * @param date
     * @return
     */
    public static Date getBeforeDay235959(Date date) {
        return addDay(getYYYYMMDD235959(date), -1);
    }

    /**
     * 得到指定日期的0时
     *
     * @param date
     * @return
     */
    public static Date getYYYYMMDD000000(Date date) {

        try {
            return strToDate(formatDate(date, parsePatterns[0]) + " 00:00:00", parsePatterns[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 得到指定日期的24时
     *
     * @param date
     * @return
     */
    public static Date getYYYYMMDD235959(Date date) {

        try {
            return strToDate(formatDate(date, parsePatterns[0]) + " 23:59:59", parsePatterns[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date getNow() {
        return new Date();
    }
}
