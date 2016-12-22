package presentation.manage.accessor;

import vo.hotel.HotelVo;

public interface HotelManageInfoAccessor {
	
	public HotelVo getModifiedHotelVo();
	
	public int getPasswordHash();
	
	public String getHotelId();
	
	public void setPassword(String password);
	
	public void setId(String id);
	
	public void setName(String name);
	
	public void setStar(int star);
	
	public void setHotelVo(HotelVo vo);
	
	public void deleteHotel(String id);
}
