package presentation.hotel.manager;

import java.util.List;
import java.util.Set;

import utils.info.hotel.Room;

public interface HotelRoomManager {
	public boolean setRooms(Set<Room> rooms);
	
	public List<String> getNames();
}
