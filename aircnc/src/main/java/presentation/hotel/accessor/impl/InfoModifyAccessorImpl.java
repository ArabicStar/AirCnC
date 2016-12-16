package presentation.hotel.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.hotel.accessor.InfoModifyAccessor;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public class InfoModifyAccessorImpl implements InfoModifyAccessor {

	private static InfoModifyAccessor instance;

	private String introduction;
	
	private String location;
	
	private String scope;
	
	private String equipment;
	
	private int password;
	
	private Room room;

	public static final InfoModifyAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new InfoModifyAccessorImpl();
	}

	public static final InfoModifyAccessor getInstance() {
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
	public void setScope(String scope) {
		this.scope = scope;

	}

	@Override
	public void setLocation(String location) {
		this.location = location;

	}

	@Override
	public void setIntro(String intro) {
		this.introduction = intro;

	}

	@Override
	public void setEquip(String equip) {
		this.equipment = equip;

	}

	@Override
	public HotelVo getModifyHotelInfo() {
		HotelVoBuilder builder = new HotelVoBuilder().setIntro(introduction).setEquipment(equipment).
				setLocation(location).setScope(scope);
		
		return builder.getHotelInfo();
	}


	@Override
	public void setPassword(String password) {
		this.password = password.hashCode();
		
	}

	@Override
	public int getPasswordHash() {		
		return password;
	}

	@Override
	public Room getRoom() {
		return room;
	}

	@Override
	public void setRoom(String name, int peopleNum, int roomNum, double price) {
		RoomBuilder builder = new RoomBuilder(name).setPeopleNum(peopleNum).
				setPrice(price).setRoomNum(roomNum);
		room = builder.getRoomInfo();
		
	}

}
