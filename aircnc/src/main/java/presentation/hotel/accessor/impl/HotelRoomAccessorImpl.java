package presentation.hotel.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.hotel.accessor.HotelRoomAccessor;
import presentation.hotel.accessor.InfoModifyAccessor;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;

public class HotelRoomAccessorImpl implements HotelRoomAccessor{

	private static HotelRoomAccessorImpl instance;
	private String roomName;
	private int roomNum;
	
	public static final HotelRoomAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelRoomAccessorImpl();
	}

	public static final HotelRoomAccessor getInstance() {
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
	public boolean setRoomName(String roomName) {
		if(roomName!=null&&roomName!=""){
			this.roomName = roomName;
			return true;
		}
		return false;
		
	}

	@Override
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
		
	}

	@Override
	public Room getRoom() {
		RoomBuilder builder = new RoomBuilder(roomName).setRoomNum(roomNum);
		return builder.getRoomInfo();
	}

}
