package presentation.hotel.accessor;

import java.util.Set;

import utils.info.hotel.Room;
import vo.hotel.HotelVo;

public interface InfoModifyAccessor {
	
	
	public void setScope(String scope);
	
	public void setLocation(String location);
	
	public void setIntro(String intro);
	
	public void setEquip(String equip);
	
	public HotelVo getModifyHotelInfo();
	
	public void setPassword(String password);
	
	public int getPasswordHash();

}
