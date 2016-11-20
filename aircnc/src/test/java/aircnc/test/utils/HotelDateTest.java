package aircnc.test.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utils.HotelDate;

public class HotelDateTest {

	@Test
	public void getGapDaysTest1() {
		HotelDate h1 = new HotelDate(2015, 1, 1);
		HotelDate h2 = new HotelDate(2016, 1, 1);
		assertEquals(h1.getGapDays(h2), 365);
	}
	
	@Test
	public void getGapDaysTest2() {
		HotelDate h1 = new HotelDate(2015, 3, 1);
		HotelDate h2 = new HotelDate(2016, 3, 1);
		assertEquals(h2.getGapDays(h1), 366);
	}
	
	@Test
	public void delayTimeTest1() {
		HotelDate h1 = new HotelDate(2016, 11, 18);
		assertEquals(h1.toString(), "2016 11 18 21 0");
	}
	
	@Test
	public void delayTimeTest2() {
		HotelDate h1 = new HotelDate(2016, 11, 18);
		h1.delayTime(4, 0);
		assertEquals(h1.toString(), "2016 11 19 1 0");
	}
	
	@Test
	public void delayTimeTest3() {
		HotelDate h1 = new HotelDate(2016, 2, 28);
		h1.delayTime(4, 30);
		assertEquals(h1.toString(), "2016 2 29 1 30");
	}
	
	@Test
	public void delayTimeTest4() {
		HotelDate h1 = new HotelDate(2015, 2, 28);
		h1.delayTime(4, 30);
		assertEquals(h1.toString(), "2015 3 1 1 30");
	}
	
	@Test
	public void delayTimeTest5() {
		HotelDate h1 = new HotelDate(2016, 2, 29);
		h1.delayTime(4, 30);
		assertEquals(h1.toString(), "2016 3 1 1 30");
	}
	
	@Test
	public void delayTimeTest6() {
		HotelDate h1 = new HotelDate(2016, 2, 29);
		h1.setHour(23);
		h1.setMinute(30);
		h1.delayTime(4, 30);
		assertEquals(h1.toString(), "2016 3 1 4 0");
	}
	
	@Test
	public void isValidTest1() {
		HotelDate h1 = new HotelDate(2016, 1, 1);
		assertEquals(true, h1.isValid());
	}
	
	@Test
	public void isValidTest2() {
		HotelDate h1 = new HotelDate(2016, 1, 32);
		assertEquals(false, h1.isValid());
	}
	
	@Test
	public void isValidTest3() {
		HotelDate h1 = new HotelDate(2016, 2, 29);
		assertEquals(true, h1.isValid());
	}
	
	@Test
	public void isValidTest4() {
		HotelDate h1 = new HotelDate(2016, 2, 30);
		assertEquals(false, h1.isValid());
	}
	
	@Test
	public void isValidTest5() {
		HotelDate h1 = new HotelDate(2015, 2, 1);
		assertEquals(false, h1.isValid());
	}

	@Test
	public void isValidTest6() {
		HotelDate h1 = new HotelDate(2017, 2, 1);
		assertEquals(false, h1.isValid());
	}
	
	@Test
	public void isValidTest7() {
		HotelDate h1 = new HotelDate(2100, 2, 1);
		assertEquals(false, h1.isValid());
	}
}
