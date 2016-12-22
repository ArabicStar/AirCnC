package aircnc.test.service.hotel;

import static aircnc.test.service.hotel.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.hotel.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.hotel.DataPrepareHelper.testName;
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Test;

import service.hotel.HotelAccountService;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;


public class HotelAccountServiceTest {
	private HotelAccountService acc = DataPrepareHelper.acc;
	
	public HotelAccountServiceTest(){
		prepareTestStatistic();
	}


	@Test
	public void testRegister() {
		HotelVoBuilder b = new HotelVoBuilder().setName("bbHotel").setStar(4);
		HotelInfo v = acc.register(b, "12345678".hashCode());
		String registeredName = v.getName();
		
		assertEquals(true, acc.existsHotel(registeredName));
	}

	@Test
	public void testLogin() {
		HotelInfo v2 = null;
		try {
			v2 = acc.login(testName(), "12345678".hashCode());	

		} catch (Exception e) {
			
		}
		assertEquals(testName(), StringUtils.deleteWhitespace(v2.getName()));
		assertEquals(testName(), StringUtils.deleteWhitespace(acc.getCurrentAccount().getName()));
		assertEquals(true, acc.isLogined());
	}

	@Test
	public void testLogout() {
		assertEquals(true, acc.logout());
		assertEquals(false, acc.isLogined());
	}

	@Test
	public void testExistsHotel() {
		boolean res1 = false, res2 = false;
		try {
			res1 = acc.existsHotel(testName());
//			res1 = acc.existsHotel("bbHotel");
			res2 = acc.existsHotel("oldHotel");
		} catch (Exception e) {
		}
		assertEquals(true, res1);
		assertEquals(false, res2);
	}


	@After
	public void tearDown() {
		dumpTestStatistic();
	}
}
