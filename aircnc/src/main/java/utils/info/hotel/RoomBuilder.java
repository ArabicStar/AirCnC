package utils.info.hotel;

import static utils.exception.StaticExceptionFactory.*;

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
public class RoomBuilder extends RoomTemplate {
	private static final Room INVALID_ROOM;
	static {
		INVALID_ROOM = new Room(Type.其它);
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

	public RoomBuilder(String typeName) {
		this(Type.of(typeName));
		this.name = typeName;
	}

	public RoomBuilder(Type type) {
		this();
		if (type == null)
			throw illegalArgEx("room type", type);

		this.type = type;
		this.numOfPeople = type.ordinal() + 1;
		this.name = type.name();
	}

	/**
	 * Initialize a builder by given RoomInfo, all information will be kept..
	 * <br>
	 * 
	 * @param type
	 */
	public RoomBuilder(Room info) {
		this(info.getType());
		this.setName(info.getName()).setPeopleNum(info.getPeopleNum()).setRoomNum(info.getRoomNum())
				.setPrice(info.getPrice()).setId(info.getId()).setHotel(info.getHotel());
	}

	/**
	 * Set name. <br>
	 * 
	 * @param name
	 *            name string <br>
	 * @return this instance <br>
	 */
	public RoomBuilder setName(String name) {
		if (type == Type.其它)
			this.name = name;
		return this;
	}

	/**
	 * Set peopleNum. <br>
	 * 
	 * @param peopleNum
	 *            peopleNum int <br>
	 * @return this instance <br>
	 */
	public RoomBuilder setPeopleNum(int peopleNum) {
		if (type == Type.其它 && checkPeopleNum(peopleNum))
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
		return (name != null) && checkName(name) && checkPeopleNum(numOfPeople) && checkRoomNum(numOfRoom);
	}

	public Room getRoomInfo() {
		if (!isReady())
			throw illegalStateException("Room builder not set up");

		return new Room(type).setName(name).setPeopleNum(numOfPeople).setRoomNum(numOfRoom).
				setPrice(price).setId(id);
	}

}
