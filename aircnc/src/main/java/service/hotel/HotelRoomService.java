package service.hotel;

import vo.hotel.RoomVo;

public interface HotelRoomService {

	public RoomVo getCheapestRoom(final int hotelId);
	
	public boolean updateAvailableRoom(String id, RoomVo room);
}
