package aircnc.test.service.hotel;

import static aircnc.test.service.member.DataPrepareHelper.testID;
import static aircnc.test.service.member.DataPrepareHelper.testName;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.dao.hotel.HotelDao;
import data.dao.impl.hotel.HotelDaoImpl;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import service.impl.hotel.HotelAccountManager;
import service.impl.hotel.HotelInfoManager;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import utils.info.member.MemberInfo;
import vo.hotel.HotelVoBuilder;

public class HotelInfoServiceTest {
	private HotelAccountService acc;
	private HotelInfoService info;
	private HotelDao dao;

	private String name = "prepareHotel";

	@Before
	public void setUp() throws Exception {
		dao = HotelDaoImpl.INSTANCE;
		
		acc = new HotelAccountManager(dao);

		info = new HotelInfoManager(acc,dao,null,null);
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
		acc.login(name, "12345678".hashCode());
	}

	@Test
	public void testGetMemberInfo() {
		HotelInfo v = info.getHotelInfo(name);
		info.updateInfo(v);
		assertEquals(name, v.getName());
	}
	
	@Test
	public void testCheapestRoom() {
		double cheapest = info.getCheapestPrice(name);
		assertEquals(245.0, cheapest,0.01);
		assertEquals(null, info.getHotelComment(13));
		assertEquals(null, info.getHotelPromotion(13));
		assertEquals(null, info.getHotelOrder(13));
		assertEquals(true, info.updateInfo(info.getHotelInfo(name)));
	}
	
	
	@Test
	public void testUpdate() {
		
		assertEquals(true, info.updateInfo(info.getHotelInfo(name)));
	}
	
	@Test
	public void testHotelOrder() {

		assertEquals(null, info.getHotelOrder(13));

	}
	
	@Test
	public void testHotelPromotion() {
		
		assertEquals(null, info.getHotelPromotion(13));

	}



	@After
	public void tearDown() {
		try {
//			dao.deleteHotel(name);
		} catch (Exception e) {
		}
	}
}
