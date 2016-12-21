package aircnc.test.service.hotel;

import static aircnc.test.service.hotel.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.hotel.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.hotel.DataPrepareHelper.testName;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import service.hotel.HotelInfoService;
import utils.info.hotel.HotelInfo;

public class HotelInfoServiceTest {

	private HotelInfoService info = DataPrepareHelper.info;
	
	public HotelInfoServiceTest(){
		prepareTestStatistic();
	}

	@Test
	public void testGetHotelInfo() {
		HotelInfo v = info.getHotelInfo(testName());
		assertEquals(2, v.getRooms().size());
	}
	
//	@Test
//	public void testCheapestRoom() {
//		double cheapest = info.getCheapestPrice(testName());
//		assertEquals(300.0, cheapest,0.01);
//	}
//	
//	
//	@Test
//	public void testUpdate() {
//		
//		assertEquals(true, info.updateInfo(info.getHotelInfo(testName())));
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



	@After
	public void tearDown() {
		try {
			dumpTestStatistic();
		} catch (Exception e) {
		}
	}
}
