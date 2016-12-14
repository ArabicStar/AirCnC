package presentation.hotel.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;

import presentation.hotel.accessor.InfoModifyAccessor;
import vo.hotel.HotelVo;
import vo.hotel.RoomVo;

public class InfoModifyAccessorImpl implements InfoModifyAccessor {

	private static InfoModifyAccessor instance;

	private String introduction;
	
	private String location;
	
	private String scope;
	
	private String equipment;
	
	private Set<RoomVo> rooms;

	private HotelVo hotel;

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
	public HotelVo getModifyHotelVo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRooms(Set<RoomVo> rooms) {
		this.rooms = rooms;

	}

}
