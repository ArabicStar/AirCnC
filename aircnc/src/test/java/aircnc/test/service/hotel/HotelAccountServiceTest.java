package aircnc.test.service.hotel;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import data.dao.hotel.HotelDao;
import data.dao.impl.hotel.HotelDaoImpl;
import service.hotel.HotelAccountService;
import service.impl.hotel.HotelAccountManager;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;


public class HotelAccountServiceTest {
	private HotelAccountService acc;
	private HotelDao dao;

	private String name = "prepareHotel";

	@Before
	public void setUp() throws Exception {
		dao = HotelDaoImpl.INSTANCE;
		acc = new HotelAccountManager(dao);
		HotelVoBuilder b = new HotelVoBuilder().setName(name).setStar(4);
		HotelInfo v = acc.register(b, "12345678".hashCode());
	}

	@Test
	public void testRegister() {
		HotelVoBuilder b = new HotelVoBuilder().setName("newHotel").setStar(4);
		HotelInfo v = acc.register(b, "12345678".hashCode());
		String registeredName = v.getName();
		
		assertEquals(true, acc.existsHotel(registeredName));
		dao.deleteHotel(registeredName);
	}

	@Test
	public void testLogin() {
		HotelInfo v2 = null;
		try {
			v2 = acc.login("prepareHotel", "12345678".hashCode());	

		} catch (Exception e) {
		}
		assertEquals("prepareHotel", v2.getName());
		assertEquals("prepareHotel", acc.getCurrentAccount().getName());
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
			res1 = acc.existsHotel("prepareHotel");
			res2 = acc.existsHotel("oldHotel");
		} catch (Exception e) {
		}
		assertEquals(true, res1);
		assertEquals(false, res2);
	}


	@After
	public void tearDown() {
		try {
			dao.deleteHotel(name);
		} catch (Exception e) {
		}
	}
}
