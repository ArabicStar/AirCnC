package po.hotel;

import utils.info.hotel.RoomInfo;
import utils.info.hotel.RoomInfoBuilder;

public class RoomPoBuilder extends RoomInfoBuilder {
	
	private static final RoomPo INVALID_ROOM_PO;
	static {
		INVALID_ROOM_PO = new RoomPo(null);
		INVALID_ROOM_PO.invalidate();
	}

	protected RoomPoBuilder(RoomInfo info) {
		this(info.getType());
		this.setName(info.getName()).setPeopleNum(info.getPeopleNum()).setRoomNum(info.getRoomNum());
	}
	
	protected RoomPoBuilder(String type){
		super(type);
	}
	
	/**
	 * Get an invalid RoomPo instance.
	 * 
	 * @return Invalid RoomPo instance
	 */
	public static final RoomPo getInvalidInfo() {
		return INVALID_ROOM_PO;
	}

	@Override
	public RoomPoBuilder setName(String name) {
		if (checkName(name))
			// insert blank space to avoid injection attack
			this.name = name.replaceAll("(.{1})", "$1 ");
		else
			throw new IllegalArgumentException("Wrong room name");

		return this;
	}

	@Override
	public RoomPo getRoomInfo() {
		if (isReady())
			if (type == Type.OTHER)
				return new RoomPo(Type.OTHER).setName(name).setPeopleNum(numOfPeople).setRoomNum(numOfRoom);
			else
				return new RoomPo(type).setRoomNum(numOfRoom);

		return new RoomPoBuilder("single").getRoomInfo();
	}
	
	public RoomPoBuilder setPeopleNum(int peopleNum){
		this.setPeopleNum(peopleNum);
		return this;
	}
	
	public RoomPoBuilder setRoomNum(int roomNum){
		this.setRoomNum(roomNum);
		return this;
	}

}
