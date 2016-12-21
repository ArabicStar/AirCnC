package aircnc.test.service.hotel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.dao.hotel.HotelDao;
import data.dao.impl.hotel.HotelDaoImpl;
import data.dao.query.HotelQueryDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import service.impl.hotel.HotelAccountManager;
import service.impl.hotel.HotelInfoManager;
import service.promotion.HotelPromotionManagementService;
import service.query.CommentQueryService;
import service.query.OrderQueryService;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import utils.info.hotel.RoomTemplate.Type;

public class DataPrepareHelper {
	
	
	private static final int testStar = 1;
	private static final double testGrade = 5;
	private static final String testName = "DDhotel";
	private static final String testScope = "new way";
	private static final String testLocation = "newnewnew";
	private static final String testIntro = "zzzzz";
	private static final String testEquip = "wifi;停车场;24小时热水;";
	private static final int testPass = "12345678".hashCode();
	// private static final String[] testType = new String[] { "单人间", "双人间" };
	private static final Type[] testType = new Type[] { Type.单人间, Type.双人间 };
//	private static final int[] testPnum = new int[] { 10, 2 };
	private static final int[] testRnum = new int[] { 100, 20 };
	public static HotelPo testData = null;
	
	static{
		HotelPoBuilder b = new HotelPoBuilder().setName(testName).setGrade(testGrade).setIntro(testIntro)
				.setPasswordHash(testPass).setScope(testScope).setLocation(testLocation).
				setStar(testStar).setEquipment(testEquip);

		Set<Room> rooms = new HashSet<Room>();

		for (int j = 0; j < 2; j++) {
			RoomBuilder r = new RoomBuilder(testType[j]).setPrice(300).setRoomNum(testRnum[j]);
			Room room = r.getRoomInfo();
			rooms.add(room);
		}

		b.setRooms(rooms);
		testData = b.getHotelInfo();
	}
	
	
	public static final HotelDao hotelDao = HotelDaoImpl.INSTANCE;
	public static final HotelQueryDao hotelQueryDao = null;
	private static final OrderQueryService orderQueryService = null;
	private static final CommentQueryService commentQueryService = null;
	private static final HotelPromotionManagementService promotionService = null;
	public static final HotelAccountService acc = HotelAccountManager.launch(hotelDao);
	
	public static final HotelInfoService info = HotelInfoManager.launch(hotelDao, hotelQueryDao, acc, orderQueryService, promotionService,commentQueryService);
	
	public static final void prepareTestStatistic() {
		hotelDao.addHotel(testData);
		testData = hotelDao.findHotelByName(testName);
	}

	public static final void dumpTestStatistic() {
		hotelDao.deleteHotel(testData.getId());
	}
	
	public static final String testName() {
		return testName;
	}
}
