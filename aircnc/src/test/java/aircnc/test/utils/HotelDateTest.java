package aircnc.test.utils;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import utils.HotelDate;

public class HotelDateTest {

	@Test
	public void getGapDaysTest1() {
		/**
		 * TODO:实例化h1, h2
		 * 改变使用getGapDays的使用方法
		 */
		LocalDateTime h1 = null;
		LocalDateTime h2 = null;
		//2015, 2, 28
		//2016, 2, 28
//		assertEquals(h1.getGapDays(h2), 365);
	}
	
	@Test
	public void getGapDaysTest2() {
		/**
		 * TODO:实例化h1, h2
		 * 改变使用getGapDays的使用方法
		 */
		LocalDateTime h1 = null;
		LocalDateTime h2 = null;
//		******* h1 = new HotelDate(2015, 3, 1);
//		******* h2 = new HotelDate(2016, 3, 1);
//		assertEquals(h2.getGapDays(h1), 366);
	}
	
	@Test
	public void delayTimeTest1() {
		/**
		 * TODO:实例化h1
		 * 改变方法来显示日期和时间信息
		 */
		LocalDateTime h1 = null;
//		******* h1 = new HotelDate(2016, 11, 18);
//		assertEquals(h1.toString(), "2016 11 18 21 0");
	}
	
	@Test
	public void delayTimeTest2() {
		/**
		 * TODO:实例化h1
		 * 改变方法来显示日期和时间信息
		 */
		LocalDateTime h1 = null;
//		LocalDateTime h1 = new HotelDate(2016, 11, 18);
//		h1.delayTime(4, 0);
//		assertEquals(h1.toString(), "2016 11 19 1 0");
	}
	
	@Test
	public void delayTimeTest3() {
		/**
		 * TODO:实例化h1
		 * 改变方法来显示日期和时间信息
		 */
		LocalDateTime h1 = null;
//		******* h1 = new HotelDate(2016, 2, 28);
//		h1.delayTime(4, 30);
//		assertEquals(h1.toString(), "2016 2 29 1 30");
	}
	
	@Test
	public void delayTimeTest4() {
		/**
		 * TODO:实例化h1
		 * 改变方法来显示日期和时间信息
		 */
		LocalDateTime h1 = null;
//		******* h1 = new HotelDate(2015, 2, 28);
//		h1.delayTime(4, 30);
//		assertEquals(h1.toString(), "2015 3 1 1 30");
	}
	
	@Test
	public void delayTimeTest5() {
		/**
		 * TODO:实例化h1
		 * 改变方法来显示日期和时间信息
		 */
		LocalDateTime h1 = null;
//		******* h1 = new HotelDate(2016, 2, 29);
//		h1.delayTime(4, 30);
//		assertEquals(h1.toString(), "2016 3 1 1 30");
	}
	
	@Test
	public void delayTimeTest6() {
		/**
		 * TODO:实例化h1
		 * 改变方法来显示日期和时间信息
		 * 使用Java自带的API进行时间的修改
		 */
		LocalDateTime h1 = null;
//		******* h1 = new HotelDate(2016, 2, 29);
//		h1.setHour(23);
//		h1.setMinute(30);
//		h1.delayTime(4, 30);
//		assertEquals(h1.toString(), "2016 3 1 4 0");
	}
	

}
