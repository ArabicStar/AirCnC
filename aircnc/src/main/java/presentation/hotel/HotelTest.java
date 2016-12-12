package presentation.hotel;

import presentation.hotel.manager.InfoManager;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public class HotelTest {
	InfoManager hotelInfoManger;
	
	HotelVo hotel;
	
	public HotelTest(){
		HotelVoBuilder builder = new HotelVoBuilder().setID(2).setGrade(4.8).setEquipment("wifi;");
	}
}
