package po.hotel;

import org.apache.commons.lang.StringUtils;

import utils.info.hotel.RoomInfo;

public class RoomPo extends RoomInfo{

	protected RoomPo(Type type) {
		super(type);
	}

	@Override
	public String getName() {
		if (isValid())
			return (type == Type.OTHER ? StringUtils.deleteWhitespace(this.name):type.name().toLowerCase());
		return null;
	}
	
	RoomPo setName(String name){
		this.name = name;
		return this;
	}
	
	RoomPo setPeopleNum(int num){
		this.numOfPeople = num;
		return this;
	}
	
	RoomPo setRoomNum(int num){
		this.numOfRoom = num;
		return this;
	}

}
