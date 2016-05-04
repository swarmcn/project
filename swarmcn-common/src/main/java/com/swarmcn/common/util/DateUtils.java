package com.swarmcn.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateUtils {

	public final static long DAY_MILLISECOND = 24 * 60 * 60 * 1000;// 一天的毫秒数
	public final static int HOUR_MILLISECOND = 60 * 60 * 1000;// 一小时的毫秒数
	public final static int MIN_MILLISECOND = 60 * 1000;// 一分钟的毫秒数
	public final static int SEC_MILLISECOND = 1000;// 一秒的毫秒数

	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	public final static String YYYY_MM_DD = "yyyy-MM-dd";

	public final static String YYYYMMDD = "yyyyMMdd";

	public final static String YYYYMM = "yyyy-MM";

	public final static String HH_MM_SS = "HH:mm:ss";

	// 计算特定日期是否在该区间内
	public static boolean contains(Date startDate, Date endDate, Date currDate) {
		Interval i = new Interval(new DateTime(startDate), new DateTime(endDate));
		return i.contains(new DateTime(currDate));
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date
	 *            java.util.Date对象
	 * @param formatter
	 * @return
	 */
	public static final String dateToString(Date date, String timeFormatter) {
		if (date == null) {
			return null;
		}
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(timeFormatter);
	}

	public static final String dateToString(Long date, String formatter) {
		return dateToString(new Date(date), formatter);
	}

	/**
	 * 获取当前事件字符串
	 */
	public static final String currentDateStr() {
		return dateToString(new Date(), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 将指定的字符串解析成日期类型
	 * 
	 * @param dateStr
	 *            字符串格式的日期
	 * @return
	 */
	public static Date stringToDate(String dateStr, String pattern) {
		DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
		return format.parseDateTime(dateStr).toDate();
	}

	public static Date stringToDate(String dateStr) {
		if (dateStr.length() == 7) {
			return stringToDate(dateStr, YYYYMM);
		} else if (dateStr.length() == 10) {
			return stringToDate(dateStr, YYYY_MM_DD);
		} else if (dateStr.length() == 19) {
			return stringToDate(dateStr, YYYY_MM_DD_HH_MM_SS);
		} else {
			try {
				return stringToDate(dateStr, YYYY_MM_DD_HH_MM_SS);
			} catch (Exception e) {
				throw new RuntimeException("不支持的日期格式.");
			}
		}
	}

	/**
	 * 检查日期是否是昨天
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isYesterday(long date) {
		DateTime todayStart = getToday(0, 0, 0);
		todayStart.plusDays(-1);
		return date >= todayStart.getMillis() && date <= todayStart.getMillis() + DAY_MILLISECOND;
	}

	/**
	 * 时间差
	 * 
	 * @param source
	 *            当前时间
	 * @param target
	 *            目标时间
	 * @return
	 */
	public static long timePre(Date source, Date target) {
		return target.getTime() - source.getTime();
	}

	/**
	 * 时间差
	 * 
	 * @param source
	 *            当前时间
	 * @param target
	 *            目标时间
	 * @return
	 */
	public static long timePre(String source, String target) {
		Date sourceTime = stringToDate(source);
		Date targetTime = stringToDate(target);
		return timePre(sourceTime, targetTime);
	}

	/**
	 * 检查日期是否是今天之前的
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isBeforeToday(long date) {
		DateTime todayStart = getToday(0, 0, 0);
		return date < todayStart.getMillis();
	}

	/**
	 * 添小时
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static final Date plusHours(Date date, int hours) {
		DateTime dt = new DateTime(date);
		dt = dt.plusHours(hours);
		return dt.toDate();
	}

	/**
	 * 添日
	 * 
	 * @param date
	 * @param days
	 * @param timeFormatter
	 * @return
	 */
	public static final String plusDays(Date date, int days, String timeFormatter) {
		DateTime dt = new DateTime(date);
		dt = dt.plusDays(days);
		return dt.toString(timeFormatter);
	}

	/**
	 * 添日
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static final Date plusDays(Date date, int days) {
		DateTime dt = new DateTime(date);
		dt = dt.plusDays(days);
		return dt.toDate();
	}

	public static final String plusHours(Date date, int hours, String timeFormatter) {
		DateTime dt = new DateTime(date);
		dt = dt.plusHours(hours);
		return dt.toString(timeFormatter);
	}

	public static final String plusMins(Date date, int minutes, String timeFormatter) {
		DateTime dt = new DateTime(date);
		dt = dt.plusMinutes(minutes);
		return dt.toString(timeFormatter);
	}

	public static final Date plusMins(Date date, int minutes) {
		DateTime dt = new DateTime(date);
		dt = dt.plusMinutes(minutes);
		return dt.toDate();
	}

	public static final String plusMonths(Date date, int months, String timeFormatter) {
		DateTime dt = new DateTime(date);
		dt = dt.plusMonths(months);
		return dt.toString(timeFormatter);
	}

	public static final Date plusMonths(Date date, int months) {
		DateTime dt = new DateTime(date);
		dt = dt.plusMonths(months);
		return dt.toDate();
	}

	/**
	 * 检查日期是否在今天之后
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isAfterToday(long date) {
		DateTime todayStart = getToday(23, 59, 59);
		return date > todayStart.getMillis();
	}

	public static DateTime getToday(int hour, int min, int sec) {
		DateTime now = new DateTime();
		DateTime todayStart = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), hour, min, sec);
		return todayStart;
	}

	public static Date getTodayDate(int hour, int min, int sec) {
		DateTime now = new DateTime();
		DateTime todayStart = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), hour, min, sec);
		return todayStart.toDate();
	}

	public static DateTime getDateTime(int year, int month, int day, int hour, int min, int sec) {
		return new DateTime(year, month, day, hour, min, sec);
	}

	public static Date getDate(int year, int month, int day, int hour, int min, int sec) {
		return getDateTime(year, month, day, hour, min, sec).toDate();
	}

	/**
	 * 是否是今天
	 * 
	 * @return
	 */
	public static boolean isToday(long date) {
		DateTime todayStart = getToday(0, 0, 0);
		return date >= todayStart.getMillis() && date <= todayStart.getMillis() + DAY_MILLISECOND;
	}

	public static int getWeekDay() {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.DAY_OF_WEEK);
	}

	public static boolean isWorkDay() {
		int weekDay = DateUtils.getWeekDay();
		if (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY) {
			return false;
		}
		return true;
	}

	/**
	 * 得到今天0时时间
	 * 
	 * @return
	 */
	public static long getToday() {
		return getToday(0, 0, 0).getMillis();
	}

	/**
	 * 得到今天指定时分秒的毫秒数
	 * 
	 * @param str
	 *            HH:mm:ss
	 * @return
	 */
	public static long getTodayTime(int hour, int minute, int second) {
		return getToday(hour, minute, second).getMillis();
	}

	/**
	 * 返回当天的月数(1月返回1,依此类推)
	 * 
	 * @return
	 */
	public static int getMonth() {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.MONTH) + 1;
	}

	public static int getPreMonth() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.MONTH, -1);
		return today.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回当天的号数
	 * 
	 * @return
	 */
	public static int getDay() {
		return new DateTime().getDayOfMonth();
	}

	/**
	 * 返回指定时间的号数
	 * 
	 * @return
	 */
	public static int getDay(Date date) {
		return new DateTime(date).getDayOfMonth();
	}

	/**
	 * 取得当前月的最大天数
	 * 
	 * @return
	 */
	public static int getMaxDayInMon() {
		Calendar c = Calendar.getInstance();
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 根据日期获取所在月份最大天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMaxDayInMon(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getYear() {
		return new DateTime().getYear();
	}

	public static int getPerMonth() {
		DateTime dateTime = new DateTime();
		dateTime = dateTime.plusMonths(-1);
		return dateTime.getMonthOfYear();
	}

	public static long getDaysBetween(Date start, Date end) {
		return Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();
	}

	public static long getYearsBetween(Date start, Date end) {
		return Years.yearsBetween(new DateTime(start), new DateTime(end)).getYears();
	}

	public static long getDaysBetween(String start, String end) {
		return getDaysBetween(DateUtils.stringToDate(start), DateUtils.stringToDate(end));
	}

	public static long getDaysBetweenInTimeLevelCase(Date start, Date end) {
		return getDaysBetween(start, end);
	}

	public static long getDaysBetweenInDateLevelCase(Date start, Date end) {
		return getDaysBetween(DateUtils.dateToString(start, YYYY_MM_DD), DateUtils.dateToString(end, YYYY_MM_DD));
	}

	/**
	 * 获取指定开始时间到结束时间总共包含的月数,时间区间为:[startDate,endDate)
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static long containMonths(Date startDate, Date endDate) throws ParseException {
		return Months.monthsBetween(new DateTime(startDate), new DateTime(endDate)).getMonths();
	}

	/**
	 * 开始时间到结束时间包含的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static long containDays(Date startDate, Date endDate) throws ParseException {
		return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays();
	}

	/**
	 * 返回给定时间加上plusValue天后的字符串形式
	 * 
	 * @param date
	 *            指定时间
	 * @param plusValue
	 *            天数，或以为负数
	 * @param formatter
	 *            返回的时间格式
	 * @return
	 */
	public static String dateToString(Date date, int plusValue, String formatter) {
		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.plusDays(plusValue);
		return dateTime.toString(formatter);
	}

	/**
	 * 将给定的毫秒时间段转换成"时:分:秒"格式
	 * 
	 * @param interval
	 *            毫秒
	 * @return
	 */
	public static String getFomaterTime(long interval) {
		StringBuffer time = new StringBuffer();
		long h = interval / HOUR_MILLISECOND;
		interval = interval - h * HOUR_MILLISECOND;
		long m = interval / MIN_MILLISECOND;
		interval = interval - m * MIN_MILLISECOND;
		long s = interval / SEC_MILLISECOND;
		return time.append(h).append(":").append(m).append(":").append(s).toString();
	}

	/**
	 * 获得本周周一的日期(时间为当前时间)
	 * 
	 * @return
	 */
	public static Date getMonday() {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		cal.add(Calendar.DATE, -day_of_week);
		return cal.getTime();
	}

	/**
	 * 获取当前日期的前几天或者后几天日期
	 * 
	 * @param day
	 *            天数 负数代表前几天，正数代表后几天
	 * @return
	 */
	public static Date getDateByDay(int day) {
		// 获取当前日期
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) + day);
		return date.getTime();
	}

	/**
	 * :获取当前日期的前几年或者后几年
	 * 
	 * @param year
	 *            天数 负数代表前几年，正数代表后几年
	 * @return
	 */
	public static Date getDateByYear(int year) {
		// 获取当前日期
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, date.get(Calendar.YEAR) + year);
		return date.getTime();
	}

	/**
	 * 获取gmt格式
	 * 
	 * @param date
	 * @return
	 */
	public static final String getGMT(Date date) {
		SimpleDateFormat gmtSimpleDateFormat = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		gmtSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dt = gmtSimpleDateFormat.format(date);
		return dt;
	}

}
