package utils.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 不要删除这个类，方法要重写 这是用来处理日期计算的类
 * 
 * @author Water
 *
 */
public class HotelDate {
	@Deprecated
	private int year;

	@Deprecated
	private int month;

	@Deprecated
	private int day;

	private final int DEFAULT_HOUR = 21;

	private final int DEFAULT_MINUTE = 0;

	private int hour;

	private int minute;

	private final int[] daysOfAMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private final int[] daysOfAMonthInLeapYear = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	@Deprecated
	public HotelDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = DEFAULT_HOUR;
		this.minute = DEFAULT_MINUTE;
	}

	public HotelDate() {

	}

	/**
	 * 使用Java自带的API <br>
	 * {@link java.time.LocalDate} <br>
	 * {@link java.time.LocalTime} <br>
	 * {@link java.time.LocalDateTime} <br>
	 * 
	 * @param year
	 *            想要设定的年份
	 * @param month
	 *            想要设定的月份
	 * @param day
	 *            想要设定的天数
	 */
	@Deprecated
	public void setDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * 类的功能发生了变化，这个方法不再使用 <br>
	 * 在生成日期时，用Java自带的API，会自动检测时间是否合法，从而无需检测
	 * 
	 */
	@Deprecated
	public boolean isValid() {
		if (this.year < 2016 || this.year > 2099) {
			return false;
		}
		if (this.month < 1 || this.month > 12) {
			return false;
		}

		if (this.day < 1) {
			return false;
		}

		if (isLeapYear(this.year)) {
			if (this.day > daysOfAMonthInLeapYear[this.month]) {
				return false;
			}
		} else {
			if (this.day > daysOfAMonth[this.month]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 推迟时间<br>
	 * 由于自带的API是不会修改LocalDateTime的引用，而是传递被修改后的引用，所以用法应该如下：<br>
	 * 已有一个LocalDateTime的实体destinationDate<br>
	 * LocalDateTime excepted = HotelDate.delayTime(destinationDate, 1, 1,
	 * 0)这个样子<br>
	 * 
	 * @param destinationDate
	 *            被推迟的日期<br>
	 * @param delayedDay
	 *            延迟的天数<br>
	 * @param delayedHour
	 *            延迟的小时<br>
	 * @param delayedMinute
	 *            延迟的分钟数<br>
	 */
	public static LocalDateTime delayTime(LocalDateTime destinationDate, int delayedDay, int delayedHour,
			int delayedMinute) {
		destinationDate = destinationDate.plusDays(delayedDay).plusHours(delayedHour).plusMinutes(delayedMinute);

		return destinationDate;
	}

	/**
	 * 该方法虽然名称是show方法，但是并不打印具体信息
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String showDateAndTime(LocalDateTime dateTime) {
		String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy'/'MM'/'d HH':'mm"));

		return dateTimeStr;
	}

	/**
	 * 返回日期信息
	 */
	@Override
	@Deprecated
	public String toString() {
		return year + " " + month + " " + day + " " + hour + " " + minute;
	}

	/**
	 * 判断是否为闰年
	 * 
	 * @param year
	 *            这一年的参数
	 * @return 是闰年则返回true,否则返回false
	 */
	private boolean isLeapYear(int year) {
		if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 用来计算两个日期之间相隔的天数 计算方法遵循左闭右开的原则<br>
	 * 例如：2016.11.6 - 2016.11.7 返回值应为1 <br>
	 * @return 当前日期与目标日期的差值
	 */
	public static int getGapDays(LocalDateTime reverseDate, LocalDateTime leaveDate) {
		int gapDays = (int) reverseDate.until(leaveDate, ChronoUnit.DAYS);
		return Math.abs(gapDays);
	}

	@Deprecated
	public int getYear() {
		return year;
	}

	@Deprecated
	public void setYear(int year) {
		this.year = year;
	}

	@Deprecated
	public int getMonth() {
		return month;
	}

	@Deprecated
	public void setMonth(int month) {
		this.month = month;
	}

	@Deprecated
	public int getDay() {
		return day;
	}

	@Deprecated
	public void setDay(int day) {
		this.day = day;
	}

	@Deprecated
	public int getHour() {
		return hour;
	}

	@Deprecated
	public void setHour(int hour) {
		this.hour = hour;
	}

	@Deprecated
	public int getMinute() {
		return minute;
	}

	@Deprecated
	public void setMinute(int minute) {
		this.minute = minute;
	}

}
