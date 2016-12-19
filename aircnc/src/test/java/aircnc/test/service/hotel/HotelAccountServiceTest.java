package aircnc.test.service.hotel;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import data.dao.hotel.HotelDao;
import data.dao.impl.hotel.HotelDaoImpl;
import service.hotel.HotelAccountService;
import service.impl.hotel.HotelAccountManager;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import vo.hotel.HotelVoBuilder;


public class HotelAccountServiceTest {
	private HotelAccountService acc;
	private HotelDao dao;

	private String name = "prepareHotel";

	@Before
	public void setUp() throws Exception {
		dao = HotelDaoImpl.INSTANCE;

		acc = new HotelAccountManager(dao);
		HotelVoBuilder b = new HotelVoBuilder().setName(name).setGrade(4.8)
				.setEquipment("wifi;停车场;24小时热水;叫醒服务").setStar(7)
				.setName(name).setScope("栖霞区").setLocation("仙林大道163号")
				.setIntro("我们表面上看起来只是个学校，其实……嘿嘿嘿");
		Set<Room> rooms = new HashSet<Room>();
		RoomBuilder roombuilder = new RoomBuilder("SIGNLE").setRoomNum(50).setPrice(245);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("DOUBLE").setRoomNum(20).setPrice(400);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("TRIPLE").setRoomNum(10).setPrice(588);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("4人开黑房").setRoomNum(10).setPeopleNum(4).setPrice(698);
		rooms.add(roombuilder.getRoomInfo());
		
		b.setRooms(rooms);
		
		HotelInfo v = acc.register(b, "12345678".hashCode());
	}

	@Test
	public void testRegister() {
		HotelVoBuilder b = new HotelVoBuilder().setName("newHotel").setStar(4);
		HotelInfo v = acc.register(b, "12345678".hashCode());
		String registeredName = v.getName();
		
		assertEquals(true, acc.existsHotel(registeredName));
//		dao.deleteHotel(registeredName);
	}

	@Test
	public void testLogin() {
		HotelInfo v2 = null;
		try {
			v2 = acc.login(name, "12345678".hashCode());	

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
//		try {
//			dao.deleteHotel(name);
//		} catch (Exception e) {
//		}
	}
}
