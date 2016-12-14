package presentation.hotel.accessor;

import java.util.Set;

import vo.hotel.HotelVo;
import vo.hotel.RoomVo;

public interface InfoModifyAccessor {
	
	
	public void setScope(String scope);
	
	public void setLocation(String location);
	
	public void setIntro(String intro);
	
	public void setEquip(String equip);
	
	public HotelVo getModifyHotelVo();
	
	public void setRooms(Set<RoomVo> rooms);

}
