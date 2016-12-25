package presentation.hotel.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import presentation.hotel.manager.HotelRoomManager;
import utils.info.hotel.Room;

public class HotelRoomManagerImpl implements HotelRoomManager{

	public static HotelRoomManagerImpl instance;
	private Set<Room> rooms;
	
	public static final HotelRoomManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelRoomManagerImpl();
	}
	
	public static final HotelRoomManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean setRooms(Set<Room> rooms) {
		if(rooms!=null){
			this.rooms = rooms;
			return true;
		}
		return false;
	}
	
	@Override
	public Room getRoomByName(String name){
		return rooms.stream().filter(r->r.getName().equals(name)).iterator().next();
	}

	@Override
	public List<String> getNames() {
//		test();
		return rooms.stream().map(Room::getName).collect(Collectors.toList());
	}
	
//	public void test(){
//		rooms = new HashSet<Room>();
//		RoomBuilder builder = new RoomBuilder("单人间");
//		rooms.add(builder.getRoomInfo());
//		builder = new RoomBuilder("双人间");
//		rooms.add(builder.getRoomInfo());
//		builder = new RoomBuilder("奇怪的房间");
//		rooms.add(builder.getRoomInfo());
//		builder = new RoomBuilder("情侣间");
//		rooms.add(builder.getRoomInfo());
//	}

}
