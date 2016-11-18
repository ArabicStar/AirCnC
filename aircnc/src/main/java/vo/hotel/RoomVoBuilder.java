package vo.hotel;

import utils.info.hotel.RoomInfo;
import utils.info.hotel.RoomInfoBuilder;

public class RoomVoBuilder extends RoomInfoBuilder{
	

	protected RoomVoBuilder(RoomInfo info) {
		this(info.getType());
		this.setName(info.getName()).setPeopleNum(info.getPeopleNum()).setRoomNum(info.getRoomNum());
	}
	
	protected RoomVoBuilder(String type){
		super(type);
	}

	@Override
	public RoomVoBuilder setName(String name) {
		if (checkName(name))
			this.name = name;
		return this;
	}
	
	public RoomVoBuilder setPeopleNum(int peopleNum){
		this.setPeopleNum(peopleNum);
		return this;
	}
	
	public RoomVoBuilder setRoomNum(int roomNum){
		this.setRoomNum(roomNum);
		return this;
	}

	@Override
	public RoomInfo getRoomInfo() {
		if (!isReady())
			return null;

		if (type == Type.OTHER)
			return new RoomVo(Type.OTHER).setName(name).setPeopleNum(numOfPeople).setRoomNum(numOfRoom);
		else
			return new RoomVo(type).setRoomNum(numOfRoom);
	}

}
