package presentation.hotel.accessor;

import utils.info.hotel.Room;

public interface HotelRoomAccessor {
	public boolean setRoomName(String roomName);
	
	public void setRoomNum(int roonNum);
	
	public Room getRoom();

}
