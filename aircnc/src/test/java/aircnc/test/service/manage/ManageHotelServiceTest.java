package aircnc.test.service.manage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import data.dao.hotel.HotelDao;
import service.manage.ManageHotelService;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;


public class ManageHotelServiceTest {
	
	private ManageHotelService hot = DataPrepareHelper.hotService;
	private HotelDao hotDao = DataPrepareHelper.hotelDao;

	private int addedId;
	
	private HotelInfo info;
	
	public ManageHotelServiceTest() {
	
	}
	
	@Test
	public void testAddMarketInfo() {
		HotelVoBuilder b = new HotelVoBuilder().setGrade(4.8)
				.setEquipment("wifi;free park").setStar(7)
				.setName("ArabicStar").setScope("qixia").setLocation("xianlin")
				.setIntro("hahahaha");;
		
		HotelInfo v = hot.AddHotelInfo(b, "12345678".hashCode());
		
		this.info = v;
		
		addedId = v.getId();
		
		assertEquals(true, hotDao.existName(info.getName()));
	}
}
