package fun.peri.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class JdataUntil {

    public static String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
    public static String Y_M_D = "yyyy-MM-dd";
    public static String H_M_S = "HH:mm:ss";

    //=================日历=============================//

    /**
     *从现在向前指定的月数返回时间
     * @param months
     * @return
     */
    public static Date getPlusMonths(int months,boolean hasAll){
        DateTime dateTime = new DateTime();
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.plusMonths(months));
            return date.toDate();
        }
        return dateTime.plusMonths(months).toDate();
    }

    public static Date getPlusMonths(String time ,int months,boolean hasAll){
        DateTime dateTime = new DateTime(time);
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.plusMonths(months));
            return date.toDate();
        }
        return dateTime.plusMonths(months).toDate();
    }


    /**
     *从现在倒数指定的月数返回时间
     * @param months
     * @return
     */
    public static Date getMinusMonths(int months,boolean hasAll){
        DateTime dateTime = new DateTime();
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.minusMonths(months));
            return date.toDate();
        }
        return dateTime.minusMonths(months).toDate();
    }

    public static Date getMinusMonths(String time ,int months,boolean hasAll){
        DateTime dateTime = new DateTime(time);
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.minusMonths(months));
            return date.toDate();
        }
        return dateTime.minusMonths(months).toDate();
    }

    /**
     * 从现在倒数指定的天数返回时间
     * @param days
     * @return
     */
    public static Date getMinusDays(int days, boolean hasAll){
        DateTime dateTime = new DateTime();
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.minusDays(days));
            return date.toDate();
        }
        return dateTime.minusDays(days).toDate();
    }

    public static Date getMinusDays(String time , int days , boolean hasAll){
        DateTime dateTime = new DateTime(time);
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.minusDays(days));
            return date.toDate();
        }
        return dateTime.minusDays(days).toDate();
    }

    /**
     * 从现在向前指定的天数返回时间
     * @param days
     * @return
     */
    public static Date getPlusDays(int days, boolean hasAll){
        DateTime dateTime = new DateTime();
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.plusDays(days));
            return date.toDate();
        }
        return dateTime.plusDays(days).toDate();
    }

    public static Date getPlusDays(String time ,int days, boolean hasAll){
        DateTime dateTime = new DateTime(time);
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.plusDays(days));
            return date.toDate();
        }
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 从现在倒数指定的小时返回时间
     * @param hours
     * @return
     */
    public static Date getMinusHours(int hours){
        DateTime dateTime = new DateTime();
        return dateTime.minusHours(hours).toDate();
    }

    public static Date getMinusHours(String time ,int hours){
        DateTime dateTime = new DateTime(time);
        return dateTime.minusHours(hours).toDate();
    }

    /**
     * 从现在向前指定的小时数返回时间
     * @param hours
     * @return
     */
    public static Date getPlusHours(int hours){
        DateTime dateTime = new DateTime();
        return dateTime.plusHours(hours).toDate();
    }

    public static Date getPlusHours(String time ,int hours){
        DateTime dateTime = new DateTime(time);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 从现在倒数指定的分钟返回时间
     * @param minutes
     * @return
     */
    public static Date getMinusMinutes(int minutes){
        DateTime dateTime = new DateTime();
        return dateTime.minusMinutes(minutes).toDate();
    }

    public static Date getMinusMinutes(String time ,int minutes){
        DateTime dateTime = new DateTime(time);
        return dateTime.minusMinutes(minutes).toDate();
    }

    /**
     * 从现在向前指定的分钟数返回时间
     * @param minutes
     * @return
     */
    public static Date getPlusMinutes(int minutes){
        DateTime dateTime = new DateTime();
        return dateTime.plusMinutes(minutes).toDate();
    }

    public static Date getPlusMinutes(String time ,int minutes){
        DateTime dateTime = new DateTime(time);
        return dateTime.plusMinutes(minutes).toDate();
    }


    /**
     * 从现在向前指定的秒返回时间
     * @param seconds
     * @return
     */
    public static Date getPlusSeconds(int seconds){
        DateTime dateTime = new DateTime();
        return dateTime.plusSeconds(seconds).toDate();
    }


    public static Date getPlusSeconds(String time ,int seconds){
        DateTime dateTime = new DateTime(time);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 从现在倒数指定的秒返回时间
     * @param seconds
     * @return
     */
    public static Date getMinusSeconds(int seconds){
        DateTime dateTime = new DateTime();
        return dateTime.minusSeconds(seconds).toDate();
    }

    public static Date getMinusSeconds(String time ,int seconds){
        DateTime dateTime = new DateTime(time);
        return dateTime.minusSeconds(seconds).toDate();
    }





    /**
     * 获取当天的Date(去掉时分秒，只有年月日)
     * @return
     */
    public static Date getToday(boolean hasAll) {
        DateTime dateTime = new DateTime();
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime);
            return date.toDate();
        }
        return dateTime.toDate();
    }

    /**
     * 获取昨天的Date(去掉时分秒，只有年月日)
     * @return
     */
    public static Date getYesterday(boolean hasAll) {
        DateTime dateTime = new DateTime();
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.minusDays(1));
            return date.toDate();
        }
        return dateTime.minusDays(1).toDate();
    }

    /**
     * 获取明天的Date(去掉时分秒，只有年月日)
     * @return
     */
    public static Date getTomorrow(boolean hasAll) {
        DateTime dateTime = new DateTime();
        if (false == hasAll){
            LocalDate date = new LocalDate(dateTime.plusDays(1));
            return date.toDate();
        }
        LocalDate date = new LocalDate(dateTime.plusDays(1));
        return date.toDate();
    }

                  //===星期==//
    public static int  getDayOfWeek(){
        DateTime dateTime = new DateTime();
        return dateTime.getDayOfWeek();
    }


    public static String  getDayOfWeekText(){
        DateTime dateTime = new DateTime();
        return dateTime.dayOfWeek().getAsText();
    }

    public static int  getDayOfWeek(String time){
        DateTime dateTime = new DateTime(time);
        dateTime.dayOfWeek().getField();
        return dateTime.getDayOfWeek();
    }


    public static String getDayOfWeekText(String time){
        DateTime dateTime = new DateTime(time);
        return dateTime.dayOfWeek().getAsText();
    }
                //===星期==//

    //=================日历=============================//



    //==================格式转换======================//
    public static String conversionTime(Date time,String pattern){
        DateTime dateTime = new DateTime(time);
        return dateTime.toString(pattern);
    }
    //==================格式转换======================//

    /**
     * String类型的时间撮转LocalDate
     * @param dateStr
     * @return
     */
    public static LocalDate getLocalDate(String dateStr) {
        LocalDate localDate = null;
        if (StringUtils.isNotEmpty(dateStr)) {
            localDate = new LocalDate(Long.valueOf(dateStr));
        } else {
            localDate = new LocalDate();
        }
        return localDate;
    }

    /**
     * 获取当前毫秒数
     * @return
     */
    public static Long getMillis(){
        DateTime dateTime = new DateTime();
        return dateTime.getMillis();
    }

    public static Long getMillis(Date time){
        DateTime dateTime = new DateTime(time);
        return dateTime.getMillis();
    }

    /**
     * 根据格式获取时间字符串
     * @param pattern
     * @return
     */
    public static String getStringTime(String pattern){
        DateTime dateTime = new DateTime();
        return dateTime.toString(pattern);
    }


    public static String getStringTime(Long time ,String pattern){
        DateTime dateTime = new DateTime(time);
        return dateTime.toString(pattern);
    }

    /**
     * 获取这周开始的星期一的时间
     * @return
     */
    public static Date getThisWeekStartday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime);
        return date.dayOfWeek().withMinimumValue().toDate();
    }

    /**
     * 获取上周开始的星期一的时间
     * @return
     */
    public static Date getLastWeekStartday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.minusWeeks(1));
        return date.dayOfWeek().withMinimumValue().toDate();
    }

    /**
     * 获取这个月开始的星期一的时间
     * @return
     */
    public static Date getThisMonthStartday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime);
        return date.dayOfMonth().withMinimumValue().toDate();
    }

    /**
     * 获取上个月开始的星期一的时间
     * @return
     */
    public static Date getLastMonthStartday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.minusMonths(1));
        return date.dayOfMonth().withMinimumValue().toDate();
    }


    /**
     * 如果传递的days大于0，表示得到boundary之后的days的日期
     * 如果传递的days小于0，表示得到boundary之前的days的日期
     */
    public static Date getSpecificDay(Date boundary, int days) {
        DateTime dateTime = new DateTime(boundary);
        LocalDate date = null;
        if (days < 0) {
            date = new LocalDate(dateTime.minusDays(-days));
        } else {
            date = new LocalDate(dateTime.plusDays(days));
        }
        return date.toDate();
    }


    /**
     * 立即数：相当于高级语言中的常量（常数），它是直接出现在指令中的数，
     * 不用存储在寄存器或存储器中的数，如指令ADD AL,06H中的06H即为立即数
     * 获取两个时间的差距的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDays(Date date1, Date date2)
    {
        Date bigDay = null;
        Date smallDay = null;
        if (date1.after(date2)) {
            bigDay = date1;
            smallDay = date2;
        } else {
            bigDay = date2;
            smallDay = date1;
        }
        //return date1.getTime() / (24*60*60*1000) - date2.getTime() / (24*60*60*1000);
        return (int) (bigDay.getTime() / 86400000 - smallDay.getTime() / 86400000);//用立即数，减少乘法计算的开销
    }


    /**
     * 获取的时区时间和实际的时间差距8个小时
     * @param date
     * @param timeZoneStr
     * @return
     */
    public static Date convertByTimeZone(Date date, String timeZoneStr) {
        if (null == timeZoneStr) {
            timeZoneStr = "GMT+00";
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sf.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        Date newDate = null;
        try {
            newDate = sf1.parse(sf.format(date));
        } catch (ParseException e) {
            //logger.error(e.getMessage());
        }
        return newDate;
    }

    /**
     * 根据基准年，输出第几周的时间范围
     * @param year
     * @param WeekNum
     * @return
     */
    public static String WeekToTime(int year,int WeekNum){
        DateTime ReferenceTime = new DateTime(year, 1, 1, 0, 0, 0, 0);
        String enddata = ReferenceTime.plusWeeks(WeekNum).toString("yyyy/MM/dd");
        String startdata = ReferenceTime.plusWeeks(WeekNum).minusDays(6).toString("yyyy/MM/dd");
        return startdata+"_"+enddata;
    }

    /**
     * 截取时间
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static String InterceptSDate(String dateString) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH");
        String recordTime = sf.format(sf.parse(dateString));
        return recordTime;
    }

    /**
     * 获取前N小时
     * @param ihour
     * @return
     */
    public static String getBeforeByHourTime(int ihour){
        String returnstr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        returnstr = df.format(calendar.getTime());
        return returnstr;
    }

    /**
     * 两个时间相隔几天(date>dateStr)
     *
     * @param date
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static int distanceWhichDay(Date date, String dateStr) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date smdate=sdf.parse(dateStr);
        Date bdate=sdf.parse(sdf.format(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }
    public static void main(String[] args) throws ParseException {
//        Long a = JdataUntil.getMillis(JdataUntil.getPlusSeconds(10));
//        System.out.println(a);
//        System.out.println(JdataUntil.getStringTime(a,JdataUntil.Y_M_D_H_M_S));

//        Date dat = getMinusHours(1);
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH");
//        String date1 = JdataUntil.getBeforeByHourTime(0);
//        System.out.print(JdataUntil.InterceptSDate(date1));
    }
}
