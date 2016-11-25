package aircnc.test.utils;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import utils.date.HotelDate;

public class HotelDateTest {

	@Test
	public void getGapDaysTest1() {
		LocalDateTime h1 = LocalDateTime.of(2015, 2, 28, 12, 30, 0);
		LocalDateTime h2 = LocalDateTime.of(2015, 3, 1, 12, 30, 0);
		assertEquals(1, HotelDate.getGapDays(h1, h2));
	}

	@Test
	public void getGapDaysTest2() {
		LocalDateTime h1 = LocalDateTime.of(2015, 2, 28, 1, 30, 0);
		LocalDateTime h2 = LocalDateTime.of(2015, 3, 1, 22, 30, 0);
		assertEquals(1, HotelDate.getGapDays(h1, h2));
	}
	
	@Test
	public void getGapDaysTest3() {
		LocalDateTime h1 = LocalDateTime.of(2016, 2, 28, 1, 30, 0);
		LocalDateTime h2 = LocalDateTime.of(2016, 3, 1, 22, 30, 0);
		assertEquals(2, HotelDate.getGapDays(h1, h2));
	}
	
	@Test
	public void getGapDaysTest4() {
		LocalDateTime h1 = LocalDateTime.of(2016, 2, 28, 1, 30, 0);
		LocalDateTime h2 = LocalDateTime.of(2016, 3, 1, 22, 30, 0);
		assertEquals(2, HotelDate.getGapDays(h2, h1));
	}

	@Test
	public void showDateAndTimeTest1() {
		LocalDateTime h1 = LocalDateTime.of(2016, 11, 18, 4, 0, 0);
		assertEquals("2016/11/18 04:00", HotelDate.showDateAndTime(h1));
		
	}

	@Test
	public void delayTimeTest1() {
		LocalDateTime actual = LocalDateTime.of(2015, 2, 28, 11, 25, 30);
		actual = HotelDate.delayTime(actual, 365, 1, 35);
		LocalDateTime expected = LocalDateTime.of(2016, 2, 28, 13, 0, 30);
		assertEquals(expected, actual);
	}

	@Test
	public void delayTimeTest2() {
		LocalDateTime actual = LocalDateTime.of(2016, 11, 18, 4, 0, 0);
		actual = HotelDate.delayTime(actual, 1, 1, 0);
		LocalDateTime expected = LocalDateTime.of(2016, 11, 19, 5, 0, 0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void delayTimeTest3() {
		LocalDateTime actual = LocalDateTime.of(2016, 2, 28, 21, 0, 0);
		actual = HotelDate.delayTime(actual, 0, 4, 30);
		LocalDateTime expected = LocalDateTime.of(2016, 2, 29, 1, 30, 0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void delayTimeTest4() {
		LocalDateTime actual = LocalDateTime.of(2015, 2, 28, 21, 0, 0);
		actual = HotelDate.delayTime(actual, 0, 4, 30);
		LocalDateTime expected = LocalDateTime.of(2015, 3, 1, 1, 30, 0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void delayTimeTest5() {
		LocalDateTime actual = LocalDateTime.of(2016, 2, 29, 21, 0, 0);
		actual = HotelDate.delayTime(actual, 0, 4, 30);
		LocalDateTime expected = LocalDateTime.of(2016, 3, 1, 1, 30, 0);
		assertEquals(expected, actual);
	}

}
