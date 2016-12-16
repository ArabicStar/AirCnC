package presentation.manage.accessor;

import presentation.manage.model.HotelManageModel;
import vo.hotel.HotelVoBuilder;

public interface HotelManageInfoAccessor {
	
	public HotelVoBuilder getModifiedHotelVo();
	
	public String getHotelId();
	
	public void setId(String id);
	
	public void setName(String name);
	
	public void setPassword(String password);
	
	public void setStar(int star);
	
	public void setHotelModel(HotelManageModel model);
}
