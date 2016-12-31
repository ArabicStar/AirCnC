package service.manage;

import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public interface ManageHotelService {
	
	public HotelVo AddHotelInfo(HotelVoBuilder newHotelInfo, int passwordHash);
	
	public boolean ModifyHotelInfo(HotelInfo hotelInfo);
	
	public HotelInfo getHotelInfo(int id);
	
	public boolean deleteHotelInfo(int id);
}
