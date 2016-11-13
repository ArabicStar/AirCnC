package aircnc.test.service.hotel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import service.impl.HotelServiceImpl;
import vo.HotelVo;

public class HotelService {
	
	@Test
	public void HotelServiceTest1(){
		HotelServiceImpl hotelService = new HotelServiceImpl();
//		HotelVo hotel = new HotelVo();
		assertEquals(hotelService.getHotelLowestPrice("0001"),null);
	}
	
	@Test
	public void HotelServiceTest2(){
		HotelServiceImpl hotelService = new HotelServiceImpl();
		hotelService.addRoom(null);
	}
	
	@Test
	public void HotelServiceTest3(){
		
	}
	
	@Test
	public void HotelServiceTest4(){
		
	}

}
