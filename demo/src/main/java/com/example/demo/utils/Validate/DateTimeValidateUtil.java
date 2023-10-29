package com.example.demo.utils.Validate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间校验工具类
 */
public class DateTimeValidateUtil {

    /**
     * 判断字符串是否为合法日期格式（yyyy-MM-dd）
     *
     * @param str 待校验字符串
     *
     * @return true：是合法日期格式；false：不是合法日期格式
     */
    public static boolean isDateFormat(String str) {
        // 使用SimpleDateFormat类判断日期格式是否合法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否为合法时间格式（HH:mm:ss）
     *
     * @param str 待校验字符串
     *
     * @return true：是合法时间格式；false：不是合法时间格式
     */
    public static boolean isTimeFormat(String str) {
        // 使用SimpleDateFormat类判断日期格式是否合法
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            sdf.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否为合法时间格式（yyyy-MM-dd HH:mm:ss）
     *
     * @param str 待校验字符串
     *
     * @return true：是合法时间格式；false：不是合法时间格式
     */
    public static boolean isDateTimeFormat(String str) {
        // 使用SimpleDateFormat类判断日期格式是否合法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdf.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断给定的年份是否为闰年
     *
     * @param year 待校验年份
     *
     * @return true：是闰年；false：不是闰年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 判断给定的日期是否为周末（星期六或星期日）
     *
     * @param date 待校验日期
     *
     * @return true：是周末；false：不是周末
     */
    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    /**
     * 获取当前日期所在的周的第一天（周一）的日期
     *
     * @param date 待获取周的第一天的日期
     *
     * @return 周一的日期
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获取当前日期所在的周的最后一天（周日）的日期
     *
     * @param date 待获取周的最后一天的日期
     *
     * @return 周日的日期
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }
}
