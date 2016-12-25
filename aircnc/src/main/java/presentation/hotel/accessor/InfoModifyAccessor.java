package presentation.hotel.accessor;

import utils.info.hotel.Room;
import vo.hotel.HotelVo;

public interface InfoModifyAccessor {
	
	
	public void setScope(String scope);
	
	public void setLocation(String location);
	
	public void setIntro(String intro);
	
	public void setEquip(String equip);
	
	public void setHotel(HotelVo vo);
	
	public HotelVo getModifyHotelInfo();
	
	public void setOldPassword(String password);

	public void setNewPassword(String password);
	
	public int getOldPasswordHash();

	public int getNewPasswordHash();
	
	public void setRoom(String name,int peopleNum,int roomNum,double price);
	
	public Room getRoom();

}
