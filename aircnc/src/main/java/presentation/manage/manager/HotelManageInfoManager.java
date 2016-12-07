package presentation.manage.manager;

import presentation.manage.model.HotelManageModel;
import vo.hotel.HotelVo;

public interface HotelManageInfoManager {
	
	public boolean setHotel(HotelVo vo);
	
	public HotelManageModel getHotelInfo();
	
}
