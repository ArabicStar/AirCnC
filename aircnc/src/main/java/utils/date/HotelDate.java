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
	 * 用来计算两个日期之间相隔的天数 计算方法遵循左闭右开的原则<br>
	 * 例如：2016.11.6 - 2016.11.7 返回值应为1 <br>
	 * @return 当前日期与目标日期的差值
	 */
	public static int getGapDays(LocalDateTime reverseDate, LocalDateTime leaveDate) {
		int gapDays = (int) reverseDate.until(leaveDate, ChronoUnit.DAYS);
		return Math.abs(gapDays);
	}



}
