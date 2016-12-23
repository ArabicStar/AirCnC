
package utils.info.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import javax.persistence.Entity;

@Entity
public abstract class OrderInfoTemplate {
	protected static final String BLANK = "";

	protected String orderId;

	protected OrderStatus status;

	protected LocalDateTime entryTime;
	protected LocalDateTime lastTime;
	protected int stayDays;

	protected int roomNumber;
	protected String roomType;

	protected int peopleNumber;
	protected boolean hasChildren;

	protected double originalPrice;
	protected double discountPrice;

	protected String appeal;

	private static final DateTimeFormatter f = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4)
			.appendValue(ChronoField.MONTH_OF_YEAR, 2).appendValue(ChronoField.DAY_OF_MONTH, 2).toFormatter();

	protected static boolean isNumber(String str) {
		if (str.matches("[0-9]+")) {
			return true;
		}
		return false;
	}

	/**
	 * 年份4位<br>
	 * 月份2位<br>
	 * 日期2位<br>
	 * 酒店id至少4位<br>
	 * 订单编号至少4位<br>
	 * 前八位固定为日期，后面2n位，取酒店id和订单编号位数较长的值，在较短的前面补0
	 * 
	 * @return
	 */
	public static boolean checkOrderId(String orderId) {
		if (orderId.length() == 0)
			return true;

		if ((orderId.length() & 1) == 1)
			return false;

		if (orderId.length() < 16)
			return false;

		if (!isNumber(orderId))
			return false;

		return verifyTimeString(orderId.substring(0, 8));
	}

	public static boolean verifyTimeString(String string) {
		try {
			f.parse(string);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static final DateTimeFormatter getDateFormatter() {
		return f;
	}
}
