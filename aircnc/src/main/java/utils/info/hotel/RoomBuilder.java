package utils.info.hotel;

import static utils.exception.StaticExceptionFactory.illegalStateException;

import po.hotel.HotelPo;

/**
 * Abstract of builder for RoomInfo, assisting to assure immutable object. <br>
 * <br>
 * <b>NOTICE:</b>
 * <ul>
 * <li>If choose <b>default</b> type, peopleNum will be set automatically. Else
 * peopleNum should be set before build a RoomInfo instance.<br>
 * <li>All parameters of setter will be checked firstly. If invalid, the setting
 * will not take effect, and IllgealArgumentException will be thrown. <br>
 * </ul>
 * 
 * @author jqwu
 * @see utils.info.hotel.Room
 */
@SuppressWarnings("serial")
public class RoomBuilder extends RoomTemplate {
	private static final Room INVALID_ROOM;
	static {
		INVALID_ROOM = new Room("");
		INVALID_ROOM.invalidate();
	}

	/**
	 * Get an invalid RoomPo instance.
	 * 
	 * @return Invalid RoomPo instance
	 */
	public static final Room getInvalidInfo() {
		return INVALID_ROOM;
	}

	private RoomBuilder() {
		this.name = "";
		this.numOfPeople = -1;
		this.numOfRoom = -1;
		this.price = -1;
	}

	public RoomBuilder(String name) {
		if(!checkName(name))
			throw new IllegalArgumentException("RoomBuilder - Null Name String");
		this.name = name;
	}

	/**
	 * Initialize a builder by given RoomInfo, all information will be kept..
	 * <br>
	 * 
	 * @param type
	 */
	public RoomBuilder(Room info) {
		this(info.getName());
		this.setPeopleNum(info.getPeopleNum()).setRoomNum(info.getRoomNum())
				.setPrice(info.getPrice()).setId(info.getId()).setHotel(info.getHotel());
	}

	/**
	 * Set peopleNum. <br>
	 * 
	 * @param peopleNum
	 *            peopleNum int <br>
	 * @return this instance <br>
	 */
	public RoomBuilder setPeopleNum(int peopleNum) {
		if (checkPeopleNum(peopleNum))
			this.numOfPeople = peopleNum;

		return this;
	}

	public RoomBuilder setRoomNum(int roomNum) {
		if (checkRoomNum(roomNum))
			this.numOfRoom = roomNum;

		return this;
	}
	
	public RoomBuilder setHotel(HotelPo hotel) {
		if (hotel!=null)
			this.hotel = hotel;

		return this;
	}
	
	public RoomBuilder setId(int id) {
		this.id = id;

		return this;
	}

	public RoomBuilder setPrice(double price) {
		this.price = price;
		return this;
	}

	public boolean isReady() {
		return checkName(name) && checkPeopleNum(numOfPeople) && checkRoomNum(numOfRoom);
	}

	public Room getRoomInfo() {
		if (!isReady())
			throw illegalStateException("Room builder not set up");

		return new Room(name).setPeopleNum(numOfPeople).setRoomNum(numOfRoom).
				setPrice(price).setId(id).setHotel(hotel);
	}
	
	/**
	 * 
	 * @param modified
	 *            modified hotel information
	 * @param old
	 */
	public static final void updatePo(Room modified, Room old) {
		if (modified == null || old == null || modified == old)
			return;

		old.setPrice(modified.getPrice()).setRoomNum(modified.getRoomNum());
	}

}
