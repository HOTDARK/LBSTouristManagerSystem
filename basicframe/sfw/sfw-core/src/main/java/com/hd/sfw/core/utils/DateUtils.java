package com.hd.sfw.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 
 * @author somnuscy
 *
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	
	public static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat SDF_YYYY_MM_DD_HH = new SimpleDateFormat("yyyy-MM-dd HH");
	public static final SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat SDF_YYYY_MM = new SimpleDateFormat("yyyy-MM");
	public static final SimpleDateFormat SDF_HH_MM_SS = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat SDF_HH_MM = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat SDF_YYYY_MM_DD_ZW = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS_Z1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	public static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS_Z2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	public static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS_Z3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
	public static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS_Z4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static final SimpleDateFormat SDF_MM_DD_YYYY_HH_MM_SS_A = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US);
	
	private static List<DateFormat> formats = new ArrayList<DateFormat>();
	static {
		formats.add(SDF_YYYY_MM_DD_HH_MM_SS);
		formats.add(SDF_YYYY_MM_DD_HH_MM);
		formats.add(SDF_YYYY_MM_DD_HH);
		formats.add(SDF_YYYY_MM_DD);
		formats.add(SDF_YYYY_MM);
		formats.add(SDF_HH_MM_SS);
		formats.add(SDF_HH_MM);
		formats.add(SDF_YYYYMMDDHHMMSS);
		formats.add(SDF_YYYYMMDD);
		formats.add(DateFormat.getDateTimeInstance());
		formats.add(DateFormat.getDateInstance());
		formats.add(SDF_YYYY_MM_DD_ZW);
		formats.add(SDF_YYYY_MM_DD_HH_MM_SS_Z1);
		formats.add(SDF_YYYY_MM_DD_HH_MM_SS_Z2);
		formats.add(SDF_YYYY_MM_DD_HH_MM_SS_Z3);
		formats.add(SDF_YYYY_MM_DD_HH_MM_SS_Z4);
		formats.add(SDF_MM_DD_YYYY_HH_MM_SS_A);
	}
	
	/**
	 * 转换指定模式的时间格式
	 * @param pattern 日期模式字符串
	 * @return 日期模式
	 */
	private static SimpleDateFormat sdfPattern(String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	/**
	 * 获取当前系统时间
	 * @return 系统当前时间
	 */
	public static Date currentDate() {
		return new Date();
	}
	
	/**
	 * 获取当前系统时间字符串
	 * @param pattern 日期模式字符串
	 * @return 日期字符串
	 */
	public static String currentDateStr(String pattern) {
		return sdfPattern(pattern).format(currentDate());
	}
	
	/**
	 * 获取当前系统时间字符串
	 * @param sdf 日期模式
	 * @return 日期字符串
	 */
	public static String currentDateStr(SimpleDateFormat sdf) {
		return sdf.format(currentDate());
	}
	
	/**
	 * 字符串转化成日期,会尝试多种时间格式
	 * @param dateStr 日期字符串
	 * @return 日期
	 */
	public synchronized static Date parseDate(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		for (DateFormat df : formats) {
			try {
				return df.parse(dateStr);
			} catch (ParseException e) {
			}
		}
		return null;
	}
	
	/**
	 * 按指定模式字符串转换成日期
	 * @param dateStr 日期字符串
	 * @param pattern 日期模式字符串
	 * @return 日期
	 */
	public static Date parseDate(String dateStr, String pattern) {
		return parseDate(dateStr, sdfPattern(pattern));
	}
	
	/**
	 * 按指定模式转换成日期
	 * @param dateStr 日期字符串
	 * @param sdf 日期模式
	 * @return 日期
	 */
	public static Date parseDate(String dateStr, SimpleDateFormat sdf) {
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("根据指定的格式将字符串转换成Date出错：" + e.getMessage());
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 日期按指定模式字符串转成字符串
	 * @param date 日期
	 * @param pattern 日期模式字符串
	 * @return 日期字符串
	 */
	public static String formatStr(Date date, String pattern) {
		return formatStr(date, sdfPattern(pattern));
	}
	
	/**
	 * 日期按指定模式转成字符串
	 * @param date 日期
	 * @param sdf 日期模式
	 * @return 日期字符串
	 */
	public static String formatStr(Date date, SimpleDateFormat sdf) {
		if (date == null) {
			return null;
		}
		return sdf.format(date);
	}
	
	/**
	 * 将某个时间范围按天进行切分，未满一天的按一天算
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 日期集合
	 */
	public static List<Date> splitByDay(Date startDate, Date endDate) {
		List<Date> dayList = new ArrayList<Date>();
		String startDateStr = DateFormatUtils.format(startDate, SDF_YYYY_MM_DD.toPattern());
		Date startDate1 = DateUtils.parseDate(startDateStr);
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate1);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(startDate1);
		tempCal.add(Calendar.DAY_OF_MONTH, 1);
		while (tempCal.before(endCal)) {
			dayList.add(startCal.getTime());
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			tempCal.add(Calendar.DAY_OF_MONTH, 1);
		}
		dayList.add(startCal.getTime());
		return dayList;
	}

	/**
	 * 判断两个时间是否在同一天内
	 * @param date1 时间
	 * @param date2 时间
	 * @return true=同一天；false=非同一天
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		String date1Str = DateFormatUtils.format(date1, SDF_YYYY_MM_DD.toPattern());
		String date2Str = DateFormatUtils.format(date2, SDF_YYYY_MM_DD.toPattern());
		return date1Str.equals(date2Str);
	}

	/**
	 * 判断两个时间是否在同一个月内
	 * @param date1 时间
	 * @param date2 时间
	 * @return true=在同一个月内；false=不在同一个月内；
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个时间是否在同一季度里
	 * @param date1 时间
	 * @param date2 时间
	 * @return true=在同一个季度内；false=不在同一个季度内；
	 */
	public static boolean isSameQuarter(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int month1 = cal1.get(Calendar.MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		if (((month1 >= Calendar.JANUARY && month1 <= Calendar.MARCH) && (month2 >= Calendar.JANUARY && month2 <= Calendar.MARCH))
				|| ((month1 >= Calendar.APRIL && month1 <= Calendar.JUNE) && (month2 >= Calendar.APRIL && month2 <= Calendar.JUNE))
				|| ((month1 >= Calendar.JULY && month1 <= Calendar.SEPTEMBER) && (month2 >= Calendar.JULY && month2 <= Calendar.SEPTEMBER))
				|| ((month1 >= Calendar.OCTOBER && month1 <= Calendar.DECEMBER) && (month2 >= Calendar.OCTOBER && month2 <= Calendar.DECEMBER))) {
			return true;
		}
		return false;
	}
	
    /**
     * 把时间格式化后转换成数字，如果2014-03-04 12:12:12 转换为long型20140304121212
     * @param date 时间
     * @param sdf 格式
     * @return
     */
    public static long getFormatedTime(Date date, SimpleDateFormat sdf) {
    	if(date == null) {
    		return 0;
    	}
    	return new Long(sdf.format(date)).longValue();
    }
    
    public static String subtractParse(long deltaMillis){
    	return subtractParse(deltaMillis,"dHms");
    }
    
    /**
     * 给定时间差解析成中文表示,只计算过去的某个时间和当前时间的差
     * <pre>
     * d=>天
     * H=>小时
     * m=>分钟
     * s=>秒
     * S=>毫秒
     * 如subtractParse(119005000,"dHms")		"1天9小时3分钟25秒"
     * 如subtractParse(119005000,"Hms")		"9小时3分钟25秒"
     * 如subtractParse(119005000,"ms")		"3分钟25秒"
     * 如subtractParse(119005000,"s")		"25秒"
     * </pre>
     * @param deltaMill	时差毫秒表示
     * @return
     */
    public static String subtractParse(long deltaMillis, String format) {
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;
		long day = deltaMillis / dd;
		long hour = (deltaMillis - day * dd) / hh;
		long minute = (deltaMillis - day * dd - hour * hh) / mi;
		long second = (deltaMillis - day * dd - hour * hh - minute * mi) / ss;
		long milliSecond = deltaMillis - day * dd - hour * hh - minute * mi - second	* ss;
		StringBuilder sb = new StringBuilder();
		if (day != 0 && format.contains("d")) {
			sb.append(day + "天");
		}
		if (hour != 0 && format.contains("H")) {
			sb.append(hour + "小时");
		}
		if (minute != 0 && format.contains("m")) {
			sb.append(minute + "分");
		}
		if(format.contains("s")){
			if (milliSecond != 0) {
				sb.append(second + "." + milliSecond + "秒");
			} else {
				sb.append(second + "秒");
			}
		}
		return sb.toString();
    }
    
    /**
     * 获取某一个月最后一天
     * @param month
     * @return
     */
    public static String getEndDateOfMonth(String dat) {
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}
    
    /**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = parseDate(ddate, SDF_YYYY_MM_DD);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}
	
	/**
	 * 当前日历，这里用中国时间表示
	 * @return 以当地时区表示的系统当前日历
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}
	
	/**
	 * 指定毫秒数表示的日历
	 * @param millis
	 * @return 指定毫秒数表示的日历
	 */
	public static Calendar getCalendar(long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(millis));
		return cal;
	}
	
	/**
	 * 系统当前的时间戳
	 * @return 系统当前的时间戳
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(currentDate().getTime());
	}

	/**
	 * 指定毫秒数的时间戳
	 * @param millis 毫秒数
	 * @return 指定毫秒数的时间戳
	 */
	public static Timestamp getTimestamp(long millis) {
		return new Timestamp(millis);
	}

	/**
	 * 以字符形式表示的时间戳
	 * @param time 毫秒数
	 * @return 以字符形式表示的时间戳
	 */
	public static Timestamp getTimestamp(String time) {
		return new Timestamp(Long.parseLong(time));
	}

	/**
	 * 指定日期的时间戳
	 * @param date 指定日期
	 * @return 指定日期的时间戳
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 指定日历的时间戳
	 * @param cal 指定日历
	 * @return 指定日历的时间戳
	 */
	public static Timestamp getTimestamp(Calendar cal) {
		return new Timestamp(cal.getTime().getTime());
	}

		
		
		// ////////////////////////////////////////////////////////////////////////////
		// getWeek..
		// 各种方式获取的week
		// ////////////////////////////////////////////////////////////////////////////
		/**
		 * 取得date日期在当年的周数
		 * @param date
		 */
		public static int getWeekYear(Date date) {
			Calendar g = Calendar.getInstance();
			g.setFirstDayOfWeek(Calendar.MONDAY);//  指定从周一开始
			g.setTime(date);
			return g.get(Calendar.WEEK_OF_YEAR);
		}
		
	    // 获取当前时间所在年的最大周数
	    public static int getMaxWeekNumOfYear(int year) {
	        Calendar c = new GregorianCalendar();
	        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
	        Calendar cn = new GregorianCalendar();
	        cn.setFirstDayOfWeek(Calendar.MONDAY);
	        cn.setMinimalDaysInFirstWeek(7);
	        cn.setTime(c.getTime());
	        return cn.get(Calendar.WEEK_OF_YEAR);
	    }
		
		/**
		 * 取得date日期属于星期几
		 * @param date
		 * @return 0:星期天，1：星期一，2：星期二，3：星期三，4：星期四，5：星期五，6：星期六
		 */
		public static int getWeekDay(Date date) { 
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DAY_OF_WEEK) - 1; 
		}
		
		/**
		 * 获取某月多少周
		 */
		public static int getWeekMonth(Date date) {
			Calendar g = Calendar.getInstance();
			g.setFirstDayOfWeek(Calendar.MONDAY);//  指定从周一开始
			g.setTime(date);
			return g.getActualMaximum(Calendar.WEEK_OF_MONTH);
		}
		
		
		/**
		 * 取得某月最后一天
		 */
		public static int getMaxDay(Date date) { 
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		}
		/**
		 * 取得某月第一天
		 */
		public static int getMinDay(Date date) { 
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getActualMinimum(Calendar.DAY_OF_MONTH); 
		}
		
		/**
		 * 时间转换
		 */
		public static Date getStringToDate(String dateStr,String pattern) { 
		   try {
			return new SimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		}
		 
		/**
		 * <p>描述：根据指定日期,标识，加减的（年，月，日，时，分，秒）数，返回对应的日期 </p>
		 * <p>日期：2014-12-3 上午8:47:04 </p>
		 * @param date 指定日期
		 * @param flag 标识（1：年，2：月，3：日，4：时，5：分，6：秒）
		 * @param num 加减的（年，月，日，时，分，秒）数
		 * 				正数代表加，负数代表减
		 * @return 加减后的日期
		 */
		public static Date getAddOfSubDate(Date date, Integer flag, Integer num) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			switch (flag) {
			case 1:
				//年
				calendar.add(Calendar.YEAR, num);
				break;
			case 2:
				//月
				calendar.add(Calendar.MONTH, num);
				break;
			case 3:
				//日
				calendar.add(Calendar.DATE, num);
				break;
			case 4:
				//时
				calendar.add(Calendar.HOUR_OF_DAY, num);
				break;
			case 5:
				//分
				calendar.add(Calendar.MINUTE, num);
				break;
			case 6:
				//秒
				calendar.add(Calendar.SECOND, num);
				break;
			}
			return calendar.getTime();
		}
		
		/**
		 * <p>描述：获取两个两个日期进行比较的结果 </p>
		 * <p>日期：2014-12-3 上午10:16:53 </p>
		 * @param startTime 开始时间
		 * @param endTime 结束时间
		 * @return 返回比较结果（-1：表示EndTime大于startTime，0：表示两个时间相等，1：表示startTime大于EndTime）
		 */
		public static Integer getDateCompareTo(String startTime, String endTime, SimpleDateFormat sdf) {
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			try {
				c1.setTime(sdf.parse(startTime));
				c2.setTime(sdf.parse(endTime));
			} catch (java.text.ParseException e) {
				System.out.println("两个日期进行比较，参数时间格式不正确："+e.getMessage());
				e.printStackTrace();
			}
			return c1.compareTo(c2);
		}
		/**
		 * <p>描述：获取两个两个日期进行比较的结果 </p>
		 * <p>日期：2014-12-3 上午10:16:53 </p>
		 * @param startTime 开始时间
		 * @param endTime 结束时间
		 * @return 返回比较结果（-1：表示EndTime大于startTime，0：表示两个时间相等，1：表示startTime大于EndTime）
		 */
		public static Integer getDateCompareTo(Date startTime, Date endTime, SimpleDateFormat sdf) {
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			try {
				c1.setTime(sdf.parse(sdf.format(startTime)));
				c2.setTime(sdf.parse(sdf.format(endTime)));
			} catch (java.text.ParseException e) {
				System.out.println("两个日期进行比较，参数时间格式不正确：" + e.getMessage());
				e.printStackTrace();
			}
			return c1.compareTo(c2);
		}
		
		// ////////////////////////////////////////////////////////////////////////////
		// getMillis
		// 各种方式获取的Millis
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * <p>描述：系统时间的毫秒数 </p>
		 * <p>日期：2014-12-2 下午4:48:44 </p>
		 * @return 系统时间的毫秒数
		 */
		public static long getMillis() {
			return new Date().getTime();
		}

		/**
		 * <p>描述：指定日历的毫秒数 </p>
		 * <p>日期：2014-12-2 下午4:50:07 </p>
		 * @param cal 指定日历
		 * @return 指定日历的毫秒数
		 */
		public static long getMillis(Calendar cal) {
			return cal.getTime().getTime();
		}

		/**
		 * <p>描述：指定日期的毫秒数 </p>
		 * <p>日期：2014-12-2 下午4:50:29 </p>
		 * @param date 指定日期
		 * @return 指定日期的毫秒数
		 */
		public static long getMillis(Date date) {
			return date.getTime();
		}

		// ////////////////////////////////////////////////////////////////////////////
		// formatDate
		// 将日期按照一定的格式转化为字符串
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * <p>描述：默认方式表示的系统当前日期，具体格式：年-月-日 </p>
		 * <p>日期：2014-12-2 下午4:51:20 </p>
		 * @return 默认日期按“年-月-日“格式显示
		 */
		public static String formatDate() {
			return SDF_YYYY_MM_DD.format(getCalendar().getTime());
		}
		
		/**
		 * <p>描述：获取时间字符串 </p>
		 * <p>日期：2014-12-2 下午4:51:44 </p>
		 * @param formatstr 时间格式
		 * @return 时间字符串
		 */
		public static String getDataString(SimpleDateFormat formatstr) {
			return formatstr.format(getCalendar().getTime());
		}
		
		/**
		 * <p>描述：指定日期的默认显示，具体格式：年-月-日 </p>
		 * <p>日期：2014-12-2 下午4:52:35 </p>
		 * @param cal 指定的日期
		 * @return 指定日期按“年-月-日“格式显示
		 */
		public static String formatDate(Calendar cal) {
			return SDF_YYYY_MM_DD.format(cal.getTime());
		}

		/**
		 * <p>描述：指定日期的默认显示，具体格式：年-月-日 </p>
		 * <p>日期：2014-12-2 下午4:53:15 </p>
		 * @param date 指定的日期
		 * @return 指定日期按“年-月-日“格式显示
		 */
		public static String formatDate(Date date) {
			return SDF_YYYY_MM_DD.format(date);
		}
		
		public static Date setTingDate(Date date,int pa,int num,String paren){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(pa, num);
		    Date dateT =calendar.getTime();
		    return   getStringToDate(formatDate(dateT, paren),paren);
		}
		
		public static Date addDate(Date date,int pa,int num){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(pa, num);
		    return calendar.getTime();
		}
		
		public static Date subDate(Date date,int pa,int num){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(pa, -num);
		    return calendar.getTime();
		}
		
		

		/**
		 * <p>描述：指定毫秒数表示日期的默认显示，具体格式：年-月-日 </p>
		 * <p>日期：2014-12-2 下午4:53:35 </p>
		 * @param millis 指定的毫秒数
		 * @return 指定毫秒数表示日期按“年-月-日“格式显示
		 */
		public static String formatDate(long millis) {
			return SDF_YYYY_MM_DD.format(new Date(millis));
		}

		/**
		 * 
		 * <p>描述：默认日期按指定格式显示 </p>
		 * <p>日期：2014-12-2 下午4:53:57 </p>
		 * @param pattern 指定的格式
		 * @return 默认日期按指定格式显示
		 */
		public static String formatDate(String pattern) {
			return sdfPattern(pattern).format(getCalendar().getTime());
		}

		/**
		 * <p>描述：指定日期按指定格式显示 </p>
		 * <p>日期：2014-12-2 下午4:54:19 </p>
		 * @param cal 指定的日期
		 * @param pattern 指定的格式
		 * @return 指定日期按指定格式显示
		 */
		public static String formatDate(Calendar cal, String pattern) {
			return sdfPattern(pattern).format(cal.getTime());
		}

		/**
		 * <p>描述：指定日期按指定格式显示 </p>
		 * <p>日期：2014-12-2 下午4:54:51 </p>
		 * @param date 指定的日期
		 * @param pattern 指定的格式
		 * @return 指定日期按指定格式显示
		 */
		public static String formatDate(Date date, String pattern) {
			return sdfPattern(pattern).format(date);
		}

		// ////////////////////////////////////////////////////////////////////////////
		// formatTime
		// 将日期按照一定的格式转化为字符串
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * <p>描述：默认方式表示的系统当前日期，具体格式：年-月-日 时：分 </p>
		 * <p>日期：2014-12-2 下午4:57:13 </p>
		 * @return 默认日期按“年-月-日 时：分“格式显示
		 */
		public static String formatTime() {
			return SDF_YYYY_MM_DD_HH_MM.format(getCalendar().getTime());
		}

		/**
		 * <p>描述：指定毫秒数表示日期的默认显示，具体格式：年-月-日 时：分 </p>
		 * <p>日期：2014-12-2 下午4:57:43 </p>
		 * @param millis 指定的毫秒数
		 * @return 指定毫秒数表示日期按“年-月-日 时：分“格式显示
		 */
		public static String formatTime(long millis) {
			return SDF_YYYY_MM_DD_HH_MM.format(new Date(millis));
		}

		/**
		 * <p>描述：指定日期的默认显示，具体格式：年-月-日 时：分 </p>
		 * <p>日期：2014-12-2 下午4:58:03 </p>
		 * @param cal 指定的日期
		 * @return 指定日期按“年-月-日 时：分“格式显示
		 */
		public static String formatTime(Calendar cal) {
			return SDF_YYYY_MM_DD_HH_MM.format(cal.getTime());
		}

		/**
		 * <p>描述：指定日期的默认显示，具体格式：年-月-日 时：分 </p>
		 * <p>日期：2014-12-2 下午4:58:31 </p>
		 * @param date 指定的日期
		 * @return 指定日期按“年-月-日 时：分“格式显示
		 */
		public static String formatTime(Date date) {
			return SDF_YYYY_MM_DD_HH_MM.format(date);
		}
		
		public static String formatYmdhTime(Date date) {
			return SDF_YYYY_MM_DD_HH.format(date);
		}

		// ////////////////////////////////////////////////////////////////////////////
		// formatShortTime
		// 将日期按照一定的格式转化为字符串
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * <p>描述：默认方式表示的系统当前日期，具体格式：时：分 </p>
		 * <p>日期：2014-12-2 下午4:59:02 </p>
		 * @return 默认日期按“时：分“格式显示
		 */
		public static String formatShortTime() {
			return SDF_HH_MM.format(getCalendar().getTime());
		}

		/**
		 * <p>描述：指定毫秒数表示日期的默认显示，具体格式：时：分 </p>
		 * <p>日期：2014-12-2 下午4:59:18 </p>
		 * @param millis 指定的毫秒数
		 * @return 指定毫秒数表示日期按“时：分“格式显示
		 */
		public static String formatShortTime(long millis) {
			return SDF_HH_MM.format(new Date(millis));
		}

		/**
		 * <p>描述：指定日期的默认显示，具体格式：时：分 </p>
		 * <p>日期：2014-12-2 下午4:59:41 </p>
		 * @param cal 指定的日期
		 * @return 指定日期按“时：分“格式显示
		 */
		public static String formatShortTime(Calendar cal) {
			return SDF_HH_MM.format(cal.getTime());
		}

		/**
		 * <p>描述：指定日期的默认显示，具体格式：时：分 </p>
		 * <p>日期：2014-12-2 下午4:59:59 </p>
		 * @param date 指定的日期
		 * @return 指定日期按“时：分“格式显示
		 */
		public static String formatShortTime(Date date) {
			return SDF_HH_MM.format(date);
		}
		
		/**
		 * 时：分：秒
		 * @param date
		 * @return
		 */
		public static String formatHmsTime(Date date) {
			return SDF_HH_MM_SS.format(date);
		}
		
		/**
		 * 年-月
		 * @param date
		 * @return
		 */
		public static String formatYearMonth(Date date) {
			return SDF_YYYY_MM.format(date);
		}

		/**
		 * <p>描述：根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间 </p>
		 * <p>日期：2014-12-2 下午5:02:19 </p>
		 * @param src 将要转换的原始字符串
		 * @param pattern 转换的匹配格式
		 * @return 如果转换成功则返回转换后的日期
		 */
		public static Calendar parseCalendar(String src, String pattern) {
			Date date = parseDate(src, new SimpleDateFormat(pattern));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		}

		/**
		 * <p>描述：根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间 </p>
		 * <p>日期：2014-12-2 下午5:03:35 </p>
		 * @param src 将要转换的原始字符串
		 * @param pattern 转换的匹配格式
		 * @return 如果转换成功则返回转换后的时间戳
		 */
		public static Timestamp parseTimestamp(String src, String pattern) {
			Date date = parseDate(src, new SimpleDateFormat(pattern));
			return new Timestamp(date.getTime());
		}

		// ////////////////////////////////////////////////////////////////////////////
		// dateDiff
		// 计算两个日期之间的差值
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * <p>描述：计算两个时间之间的差值，根据标志的不同而不同 </p>
		 * <p>日期：2014-12-2 下午5:05:33 </p>
		 * @param flag 计算标志，表示按照年/月/日/时/分/秒等计算
		 * @param calSrc 减数
		 * @param calDes 被减数
		 * @return 两个日期之间的差值
		 */
		@SuppressWarnings("deprecation")
		public static int dateDiff(char flag, Date dateSrc, Date dateDes) {

			long millisDiff = getMillis(dateSrc) - getMillis(dateDes);
			if (flag == 'y') {
				Calendar calStr = Calendar.getInstance();
				calStr.setTime(dateSrc);
				Calendar calDes = Calendar.getInstance();
				calDes.setTime(dateDes);
				return (calStr.get(Calendar.YEAR) - calDes.get(Calendar.YEAR));
			}

			if (flag == 'd') {
				return (int) (millisDiff / MILLIS_IN_DAY);
			}

			if (flag == 'h') {
				return (int) (millisDiff / MILLIS_IN_HOUR);
			}

			if (flag == 'm') {
				return (int) (millisDiff / MILLIS_IN_MINUTE);
			}

			if (flag == 's') {
				return (int) (millisDiff / MILLIS_IN_SECOND);
			}

			return 0;
		}
		
		/**
		 * 两个时间之间的日期
		 * @param dBegin 开始时间
		 * @param dEnd 结束时间
		 * @return 日期字符串集合
		 */
		public static List<String> findDates(Date dBegin, Date dEnd) {
			dBegin = parseDate(formatDate(dBegin), SDF_YYYY_MM_DD);
			dEnd = parseDate(formatDate(dEnd), SDF_YYYY_MM_DD);
			List<String> dList = new LinkedList<String>();  
			dList.add(formatDate(dBegin));  
	        Calendar calBegin = Calendar.getInstance();  
	        // 使用给定的 Date 设置此 Calendar 的时间    
	        calBegin.setTime(dBegin);  
	        Calendar calEnd = Calendar.getInstance();  
	        // 使用给定的 Date 设置此 Calendar 的时间    
	        calEnd.setTime(dEnd);  
	        // 测试此日期是否在指定日期之后    
	        while (dEnd.after(calBegin.getTime())) {
	        	// 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
	            calBegin.add(Calendar.DAY_OF_MONTH, 1);  
	            dList.add(formatDate(calBegin.getTime()));  
	        }  
	        return dList;  
	   } 
		
		
		
		/**
		 * 获取某年某周的第一天
		 * 
		 * @Title:getFirstDayOfWeek
		 * @Description:
		 * @param:@param year
		 * @param:@param week
		 * @param:@return
		 * @return:String
		 * @throws
		 */
		public static String getFirstDayOfWeek(int year, int week) {
			Calendar cal = Calendar.getInstance();
			// 设置年份
			cal.set(Calendar.YEAR, year);
			// 设置周
			cal.set(Calendar.WEEK_OF_YEAR, week);
			// 设置该周第一天为星期一
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cal.setFirstDayOfWeek(Calendar.MONDAY); 
			// 格式化日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String firstDayOfWeek = sdf.format(cal.getTime());
			return firstDayOfWeek;
		}
		
		
		
		/**
		 * 获取某年某周的最后一天
		 * 
		 * @Title:getLastDayOfWeek
		 * @Description:
		 * @param:@param year
		 * @param:@param week
		 * @param:@return
		 * @return:String
		 * @throws
		 */
		public static String getLastDayOfWeek(int year, int week) {
			Calendar cal = Calendar.getInstance();
			// 设置年份
			cal.set(Calendar.YEAR, year);
			// 设置周
			cal.set(Calendar.WEEK_OF_YEAR, week);
			// 设置该周第一天为星期一
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			// 设置最后一天是星期日
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6); // Sunday
			// 格式化日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String lastDayOfWeek = sdf.format(cal.getTime());

			return lastDayOfWeek;
		}
		
		/**
		 * 判断日期是否在规定时间范围内
		 * @param strDate
		 * @param strDateBegin
		 * @param strDateEnd
		 * @return
		 */
		public static boolean isInDates(String strDate,String strDateBegin,String strDateEnd){ 
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date myDate = null;
			Date dateBegin = null;
			Date dateEnd = null;
			try {
				myDate = sd.parse(strDate);
				dateBegin = sd.parse(strDateBegin);
				dateEnd = sd.parse(strDateEnd);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			strDate = String.valueOf(myDate);
			strDateBegin = String.valueOf(dateBegin);
			strDateEnd = String.valueOf(dateEnd);
			
			int strDateH = Integer.parseInt(strDate.substring(11,13));
			int strDateM = Integer.parseInt(strDate.substring(14,16));
			int strDateS = Integer.parseInt(strDate.substring(17,19));
			
			int strDateBeginH = Integer.parseInt(strDateBegin.substring(11,13));
			int strDateBeginM = Integer.parseInt(strDateBegin.substring(14,16));
			int strDateBeginS = Integer.parseInt(strDateBegin.substring(17,19));
			
			int strDateEndH = Integer.parseInt(strDateEnd.substring(11,13));
			int strDateEndM = Integer.parseInt(strDateEnd.substring(14,16));
			int strDateEndS = Integer.parseInt(strDateEnd.substring(17,19));
			
			if((strDateH>=strDateBeginH && strDateH<=strDateEndH)){
				if(strDateH>strDateBeginH && strDateH<strDateEndH){
					return true;
				}else if(strDateH==strDateBeginH && strDateM>strDateBeginM && strDateH<strDateEndH){
					return true;
				}else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateS>strDateBeginS && strDateH<strDateEndH){
					return true;
				}else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateS==strDateBeginS && strDateH<strDateEndH){
					return true;
				}else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM<strDateEndM){
					return true;
				}else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM && strDateS<strDateEndS){
					return true;
				}else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM && strDateS==strDateEndS){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
	    }
	
	public static void main(String[] args) {
		System.out.println(getCalendar());
	}
}