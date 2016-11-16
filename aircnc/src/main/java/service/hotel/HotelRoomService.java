package service.hotel;

import vo.hotel.RoomVo;

public interface HotelRoomService {

	public boolean changeTypeAndNum(String id, RoomVo room);
	
	public boolean updateAvailableRoom(String id, RoomVo room);
}
