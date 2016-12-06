package aircnc.test.service.hotel;

import static aircnc.test.service.hotel.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.hotel.DataPrepareHelper.prepareTestStatistic;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.dao.hotel.HotelDao;
import data.dao.impl.hotel.HotelDaoImpl;
import po.hotel.HotelPoBuilder;
import service.hotel.HotelAccountService;
import service.impl.hotel.HotelAccountManager;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;


public class HotelAccountServiceTest {
	private HotelAccountService acc;
	private HotelDao dao;

	private String registeredName;

	@Before
	public void setUp() throws Exception {
		dao = new HotelDaoImpl();
		acc = new HotelAccountManager(dao);
		prepareTestStatistic(dao);
	}

	@Test
	public void testRegister() {
		HotelVoBuilder b = new HotelVoBuilder().setName("新酒店").setStar(4);
		HotelInfo v = acc.register(b, "12345678".hashCode());
		registeredName = v.getName();
		assertEquals(true, acc.existsHotel(registeredName));
	}

//	@Test
//	public void testLogin() {
//		HotelInfo v = acc.login("新酒店", "12345678".hashCode());
//		assertEquals(testName(idx), v.getName());
//		assertEquals(testName(idx), acc.getCurrentAccount().getName());
//		assertEquals(true, acc.isLogined());
//	}
//
//	@Test
//	public void testLogout() {
//		assertEquals(true, acc.logout());
//		assertEquals(false, acc.isLogined());
//	}
//
//	@Test
//	public void testExistsHotel() {
//		boolean res1 = false, res2 = false;
//		try {
//			res1 = acc.existsHotel(testID(idx));
//			res2 = acc.existsHotel("12345678");
//			res2 = acc.existsHotel("1234578");
//		} catch (Exception e) {
//		}
//		assertEquals(true, res1);
//		assertEquals(false, res2);
//	}
//
//
//	@After
//	public void tearDown() {
//		dumpTestStatistic(dao);
//		try {
//			dao.deleteHotel(registeredId);
//		} catch (Exception e) {
//		}
//	}
}
