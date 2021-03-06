package aircnc.test.service.hotel;

import static aircnc.test.service.hotel.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.hotel.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.hotel.DataPrepareHelper.testName;
import static aircnc.test.service.hotel.DataPrepareHelper.testRoom;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Test;

import po.hotel.HotelPoBuilder;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import vo.hotel.HotelVoBuilder;

public class HotelInfoServiceTest {

	private HotelInfoService info = DataPrepareHelper.info;
	private HotelAccountService acc = DataPrepareHelper.acc;
	private String newIntro = "newIntro";
	
//	public HotelInfoServiceTest(){
//		prepareTestStatistic();
//	}
//
//	@Test
//	public void testGetHotelInfo() {
//		HotelInfo v = info.getHotelInfo(testName());
//		assertEquals(2, v.getRooms().size());
//	}
	
	
	private String newname = "DDhotel";
	private String oldname = "AAhotel";
	@Test
	public void testUpdateBasic() {
		HotelInfo v = info.getHotelInfo(oldname);
//		acc.login(StringUtils.deleteWhitespace(v.getName()), "12345678".hashCode());
		HotelInfo v2 = new HotelVoBuilder(v).setName(newname).getHotelInfo();
		assertEquals(true, info.updateBasic(v2));
		v = info.getHotelInfo(newname);
		assertEquals(newname, v.getName());
	}
	
//	@Test
//	public void testUpdateRoom() {
//		HotelInfo v = info.getHotelInfo(testName());
//		
//		acc.login(StringUtils.deleteWhitespace(v.getName()), "12345678".hashCode());
//		
//		Set<Room> rooms = new HashSet<Room>();
//		rooms.add(testRoom());
//		
//		HotelInfo v2 = new HotelVoBuilder(v).setRooms(rooms).getHotelInfo();
//		assertEquals(true, info.updateInfo(v2));
//		v = info.getHotelInfo(testName());
//		assertEquals(3, v.getRooms().size());
//	}
//	
//	@Test
//	public void testHotelOrder() {
//
//		assertEquals(null, info.getHotelOrder(13));
//
//	}
//	
//	@Test
//	public void testHotelPromotion() {
//		
//		assertEquals(null, info.getHotelPromotion(13));
//
//	}



//	@After
//	public void tearDown() {
//		try {
//			dumpTestStatistic();
//		} catch (Exception e) {
//		}
//	}
}
