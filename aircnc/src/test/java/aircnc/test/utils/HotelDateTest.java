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

}
