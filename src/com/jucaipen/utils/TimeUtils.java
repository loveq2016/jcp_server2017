package com.jucaipen.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 日期Util类
 * 
 */
public class TimeUtils {
	private static String defaultDatePattern = "yyyy-MM-dd ";
	private static int age;

	/**
	 * @param date
	 * @return 判断时间是否是今日
	 */
	public static boolean isToday(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date comDate = sdf.parse(date);
			Date currrent = new Date();
			int comYear = comDate.getYear();
			int comMoth = comDate.getMonth();
			int comDay = comDate.getDate();
			int currentYear = currrent.getYear();
			int currentMoth = currrent.getMonth();
			int currentDay = currrent.getDate();
			if (currentYear == comYear && currentDay == comDay
					&& currentMoth == comMoth) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param date
	 * @return 判断是否是本月
	 */
	public static boolean isMoth(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date comDate = sdf.parse(date);
			Date currrent = new Date();
			int comYear = comDate.getYear();
			int comMoth = comDate.getMonth();
			int currentYear = currrent.getYear();
			int currentMoth = currrent.getMonth();
			if (currentYear == comYear && currentMoth == comMoth) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	// s-n>0 && s-n min
	public static int dateSort(List<String> starts) {
		/*
		 * long min= Collections.min(starts); System.out.println("min:"+min);
		 * return starts.indexOf(min);
		 */
		List<Long> startLong = new ArrayList<Long>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			long n = new Date().getTime();
			for (String start : starts) {
				long s = sdf.parse(start).getTime();
				startLong.add(s - n);
			}
			return startLong.indexOf(Collections.min(starts));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return 判断当前时间是否在设定时间内 时分秒
	 */
	public static boolean isHourLive(String startDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date start = sdf.parse(startDate);
			Date end = sdf.parse(endDate);
			Date now = new Date();
			start.setYear(now.getYear());
			start.setMonth(now.getMonth());
			start.setDate(now.getDate());

			end.setYear(now.getYear());
			end.setMonth(now.getMonth());
			end.setDate(now.getDate());

			long s = start.getTime();
			long e = end.getTime();
			long n = now.getTime();
			if (n > s && n < e) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return 判断当前时间是否在设定时间内
	 * 
	 *         start now end
	 */
	public static boolean isLive(String startDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date start = sdf.parse(startDate);
			Date end = sdf.parse(endDate);
			Date now = new Date();
			long s = start.getTime();
			long e = end.getTime();
			long n = now.getTime();
			if (n > s && n < e) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 判断时间date1是否在时间date2之前
	// 时间格式 2005-4-21 16:16:34
	public static boolean isDateBefore(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	// 判断当前时间是否在时间date2之前
	// 时间格式 2005-4-21 16:16:34
	public static boolean isDateBefore(String date2) {
		try {
			Date date1 = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return date1.before(df.parse(date2));
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean isBetwent(String start, String end) {
		try {
			Date date1 = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return (date1.before(df.parse(end)))
					&& (date1.after(df.parse(start)));
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * @param birth
	 * @return 计算两个时间相隔年份
	 */
	public static int getAge(String birth) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(defaultDatePattern);
			Date birthday = sdf.parse(birth);
			long birthM = birthday.getTime();
			long thisM = new Date().getTime();
			age = (int) ((thisM - birthM) / (1000 * 60 * 60 * 24) / 356);
			return age;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 */
	public static String format(Date date) {
		return date == null ? " " : format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? " " : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate,
				getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern)
			throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
				pattern).parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * @param n
	 * @return 当前时间之后N天的日期
	 */
	public static Date addDay(int n) {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		c.setTime(new Date());
		c.add(Calendar.DATE, n);
		Date d2 = c.getTime();
		return d2;
	}

	public static Date addBaseDay(Date dates, int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDate = sdf.format(dates);
		try {
			Date date = sdf.parse(createDate);
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			cl.add(Calendar.DATE, n);
			return cl.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		// cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
	}

	public static int getWeek() {
		int whichWeek = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			whichWeek = 7;
			break;
		case 2:
			whichWeek = 1;
			break;
		case 3:
			whichWeek = 2;
			break;
		case 4:
			whichWeek = 3;
			break;
		case 5:
			whichWeek = 4;
			break;
		case 6:
			whichWeek = 5;
			break;
		case 7:
			whichWeek = 6;
			break;
		}
		return whichWeek;
	}

	public static String getLastDate(int index) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, index); // 得到前一天
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day=calendar.get(calendar.DATE);
		return year+"-0"+month+"-"+day;
	}
	

	public static Date getDate(String year, String month, String day)
			throws ParseException {
		String result = year + "- "
				+ (month.length() == 1 ? ("0 " + month) : month) + "- "
				+ (day.length() == 1 ? ("0 " + day) : day);
		return parse(result);
	}
}