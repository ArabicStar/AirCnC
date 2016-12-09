package presentation.hotel.manager;

import presentation.hotel.model.HotelInfoModel;
import vo.hotel.HotelVo;

public interface InfoManager {
	
	public boolean setHotel(HotelVo vo);
	
	public HotelInfoModel getHotelInfo();
	

}
