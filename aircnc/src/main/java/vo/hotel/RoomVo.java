package vo.hotel;

import utils.info.hotel.RoomInfo;

public class RoomVo extends RoomInfo{

	protected RoomVo(Type type){
		super(type);
	}
	
	@Override
	public String getName() {
		if (isValid())
			return (type == Type.OTHER ? name:type.name().toLowerCase());
		return null;
	}
	
	RoomVo setName(String name){
		this.name = name;
		return this;
	}
	
	RoomVo setPeopleNum(int num){
		this.numOfPeople = num;
		return this;
	}
	
	RoomVo setRoomNum(int num){
		this.numOfRoom = num;
		return this;
	}
	
	
}
