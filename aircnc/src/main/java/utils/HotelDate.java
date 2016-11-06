package utils;

/**
 * 这是用来处理日期计算的类
 * @author Water
 *
 */
public class HotelDate {
	private int year;
	
	private int month;
	
	private int day;
	
	private final int[] daysOfAMonth = {
			0, 
			31, 28, 31, 30,
			31, 30, 31, 31,
			30, 31, 30, 31
	};
	
	private final int[] daysOfAMonthInLeapYear = {
			0,
			31, 29, 31, 30,
			31, 30, 31, 31,
			30, 31, 30, 31
	};
	
	public HotelDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public HotelDate() {
		this(2016, 1, 1);
	}
	
	public void setDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	/**
	 * 返回日期信息
	 */
	@Override
	public String toString() {
		return year + " " + month + " " + day;
	}
	
	/**
	 * 判断是否为闰年
	 * @param year 这一年的参数
	 * @return 是闰年则返回true,否则返回false
	 */
	private boolean isLeapYear(int year) {
		if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0 )) {
			return true;
		}
		return false;
	}
	
	/**
	 * 用来计算两个日期之间相隔的天数
	 * 计算方法遵循左闭右开的原则
	 * 例如：2016.11.6 - 2016.11.7 
	 * 返回值应为1
	 * @param hotelDate 目标日期
	 * @return 当前日期与目标日期的差值
	 */
	public int getGapDays(HotelDate hotelDate) {
		int tempYear = this.year;
		int tempMonth = this.month;
		int tempDay = this.day;
		int gapDays = 0;
		if(hotelDate.year < this.year || 
				(hotelDate.year == this.year && hotelDate.month < this.month) ||
				(hotelDate.year == this.year && hotelDate.month == this.month && hotelDate.day < this.day)) {
			return hotelDate.getGapDays(this);
		}
		
		while(hotelDate.year != this.year || 
				hotelDate.month != this.month ||
				hotelDate.day != this.day) {
			gapDays++;
			this.day++;
			if(!isLeapYear(this.year)) {
				if (this.day > daysOfAMonth[this.month]) {
					this.day = 1;
					this.month++;
				}
			} else {
				if (this.day > daysOfAMonthInLeapYear[this.month]) {
					this.day = 1;
					this.month++;
				}
			}
			
			
			if(this.month > 12) {
				this.month = 1;
				this.year++;
			}
		}
		this.setDate(tempYear, tempMonth, tempDay);
		return gapDays;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
}
