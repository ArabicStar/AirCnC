package po.hotel;

import java.util.List;

import utils.info.hotel.HotelInfo;
import utils.info.hotel.HotelInfoBuilder;

public class HotelPoBuilder extends HotelInfoBuilder{
	private int passwordHash = Integer.MIN_VALUE;
	private List<RoomPo> rooms = null;
	
	private static final HotelPo INVALID_HOTEL_PO;
	static {
		INVALID_HOTEL_PO = new HotelPo();
		INVALID_HOTEL_PO.invalidate();
	}
	
	/**
	 * Get an invalid HotelPo instance.
	 * 
	 * @return Invalid HotelPo instance
	 */
	public static final HotelPo getInvalidInfo() {
		return INVALID_HOTEL_PO;
	}

	/**
	 * Invalid HotelPo instance.<br>
	 * Usually, used to mark invalid access and operation, etc.<br>
	 */
	public HotelPoBuilder(HotelInfo info) {
		super(info);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HotelInfoBuilder setName(String name) {
		if (checkHotelName(name))
			// insert blank space to avoid injection attack
			this.name = name.replaceAll("(.{1})", "$1 ");
		else
			throw new IllegalArgumentException("Wrong hotel name");

		return this;
	}

	@Override
	public HotelInfo getHotelInfo() {
		if (!isReady() && passwordHash != Integer.MIN_VALUE)
			throw new IllegalStateException("Lack Of Info");
		
		return new HotelPo().setID(id).setName(name).setName(name).setPasswordHash(passwordHash).setScope(scope).setLocation(location)
				.setIntro(introduction).setStar(star).setGrade(grade).setRooms(rooms);
	}
	
	/**
	 * @param passwordHash
	 * @return this instance
	 */
	public HotelPoBuilder setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}
	
	public HotelPoBuilder setRooms(List<RoomPo> rooms){
		this.rooms = rooms;
		return this;
	}
	
	@Override
	public HotelPoBuilder setScope(String scope) {
		super.setScope(scope);
		return this;
	}
	
	@Override
	public HotelPoBuilder setLocation(String locaiton) {
		super.setLocation(locaiton);
		return this;
	}
	
	
	@Override
	public HotelPoBuilder setIntro(String intro) {
		super.setIntro(intro);
		return this;
	}
	
	
	@Override
	public HotelPoBuilder setStar(int star) {
		super.setStar(star);
		return this;
	}	
	
	
	@Override
	public HotelPoBuilder setGrade(Double grade) {
		super.setGrade(grade);
		return this;
	}

}
