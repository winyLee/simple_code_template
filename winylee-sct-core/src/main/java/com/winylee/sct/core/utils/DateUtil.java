package com.winylee.sct.core.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
    public static final long YEAR_MILLION_SECONDS = 365 * 24 * 60 * 60 * 1000;
    public static final long MONTH_MILLION_SECONDS = 30 * 24 * 60 * 60 * 1000;
    public static final long DAY_MILLION_SECONDS = 24 * 60 * 60 * 1000;
    public static final long HOUR_MILLION_SECONDS = 60 * 60 * 1000;
    public static final long MINUTE_MILLION_SECONDS = 60 * 1000;
    public static final long MILLOIN_SECONDS = 1000;

    public static final String DATE_STRING_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_STRING_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm";
    public static final String DATE_STRING_FORMAT_CN = "yyyy年MM月dd日";
    public static final String DATE_STRING_FORMAT_WEEK_CN = "yyyy/MM/dd E";
    public static final String YEAR_MONTH_DATE = "yyyy-MM-dd";
    public static final String YEAR_MONTH = "yyyy-MM";
    public static final String YEAR_MONTH_DATE_2 = "yyyy/MM/dd";
    public static final String MONTH_DATE = "MM-dd";
    public static final String DATE_FORMAT_TIME = "HH:mm";

    public static final String DATE_STRING_FORMAT_DAY = "yyyy-MM-dd";
    public static final String DATE_STRING_FORMAT_DAY_HOUR_MIN = "yyyy-MM-dd HH:mm";
    public static final String DATE_STRING_FORMAT_DAY2 = "yyyy.MM.dd";
    public static final String DATE_STRING_FORMAT_DAY3 = "yyyyMMdd";
    public static final String DATE_STRING_FORMAT_CN2 = "yyyy年MM月";
    public static final String DATE_STRING_FORMAT_CN3 = "yyyy年";

    public static final String TIME = "HH:mm:ss";
    public static final String DEFAULT = "yyyy-MM-dd";
    public static final String STANDARD = "yyyy-MM-dd HH:mm";
    public static final String ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String STANDARD_CN = "yyyy年MM月dd日 HH时mm分";
    private static final String SIMPLE_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String DATE_STRING_FORMAT_DAY_HOUR_MIN_CN = "yyyy年MM月dd日 HH时mm分";
    public static final String DATE_STRING_FORMAT_MON_DAY_HOUR_MIN_CN = "MM月dd日 HH时mm分";

    /**
     * 转化时间从长整形为指定格式日期
     *
     * @param format
     * @param time
     * @return
     */
    public static String convertDateLongToDateString(String format, Long time) {
        if (time == null || time == 0) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        Timestamp now = new Timestamp(time);
        return df.format(now);
    }
}
