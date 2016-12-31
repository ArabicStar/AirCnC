package presentation.manage.accessor;

import vo.hotel.HotelVo;

public interface HotelManageInfoAccessor {
	
	public HotelVo getModifiedHotelVo();
	
	public HotelVo getAddedHotelVo();
	
	public int getPasswordHash();
	
	public int getHotelId();
	
	public String getHotelName();
	
	public void setPassword(String password);
	
	public void setId(int id);
	
	public void setName(String name);
	
	public void setStar(int star);
	
	public void setHotelVo(HotelVo vo);
	
	public void deleteHotel(int id);
}
