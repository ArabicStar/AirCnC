package aircnc.test.service.hotel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import service.impl.HotelServiceImpl;
import utils.info.hotel.RoomInfoTemplate;
import vo.hotel.RoomVo;

public class HotelServiceTest {
	
	@Test
	public void HotelServiceTest1(){
		HotelServiceImpl hotelService = new HotelServiceImpl();
//		HotelVo hotel = new HotelVo();
		assertEquals(hotelService.getHotelLowestPrice("0001"),null);
	}
	/**
	 * 测试添加房间
	 */
	@Test
	public void HotelServiceTest2(){
		HotelServiceImpl hotelService = new HotelServiceImpl();
		RoomVo room =null;
		hotelService.addRoom(room);
		assertEquals(hotelService.getRooms(),room);
	}
	
	@Test
	public void HotelServiceTest3(){
		HotelServiceImpl hotelService = new HotelServiceImpl();
//		HotelVo hotel = new HotelVo();
		assertEquals(hotelService.getHotelSimpleInfo("0001"),null);
	}
	
	@Test
	public void HotelServiceTest4(){
		HotelServiceImpl hotelService = new HotelServiceImpl();
//		HotelVo hotel = new HotelVo();
		assertEquals(hotelService.getHotelDetailInfo("0001"),null);
	}

	@Test
	public void HotelServiceTest5(){
		HotelServiceImpl hotelService = new HotelServiceImpl();
//		HotelVo hotel = new HotelVo();
		assertEquals(hotelService.checkMyOrder("0001","0001"),null);
	}
}
