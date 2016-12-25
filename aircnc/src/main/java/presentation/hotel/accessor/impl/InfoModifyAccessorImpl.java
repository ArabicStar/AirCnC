package presentation.hotel.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.HashSet;
import java.util.Set;

import presentation.hotel.accessor.InfoModifyAccessor;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public class InfoModifyAccessorImpl implements InfoModifyAccessor {

	private static InfoModifyAccessor instance;
	
	private HotelVo hotel;

	private String introduction;
	
	private String location;
	
	private String scope;
	
	private String equipment;
	
	private int oldPwdHash;

	private int newPwdHash;
	
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
		this.room = null;

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
		HotelVoBuilder builder = new HotelVoBuilder(hotel);
		if(room!=null){
			Set<Room> rooms = new HashSet<Room>();
			rooms.add(room);
			builder.setRooms(rooms);
			return builder.getHotelInfo();
		}
		
		return builder.setIntro(introduction).setEquipment(equipment).
		setLocation(location).setScope(scope).getHotelInfo();
	}

	@Override
	public void setOldPassword(String password) {
		this.oldPwdHash = password.hashCode();
	}

	@Override
	public void setNewPassword(String password) {
		this.newPwdHash = password.hashCode();
	}

	@Override
	public int getOldPasswordHash() {
		return oldPwdHash;
	}

	@Override
	public int getNewPasswordHash() {
		return newPwdHash;
	}

	@Override
	public Room getRoom() {
		return room;
	}

	@Override
	public void setRoom(String name, int peopleNum, int roomNum, double price) {
		room = new RoomBuilder(name).setPeopleNum(peopleNum).
				setPrice(price).setRoomNum(roomNum).getRoomInfo();
		
	}

	@Override
	public void setHotel(HotelVo vo) {
		this.hotel = vo;
		
	}

}
