package vo.hotel;

import utils.info.hotel.RoomInfo;
import utils.info.hotel.RoomInfoBuilder;

public class RoomVoBuilder extends RoomInfoBuilder{
	
	private static final RoomVo INVALID_ROOM_VO;
	static {
		INVALID_ROOM_VO = new RoomVo(null);
		INVALID_ROOM_VO.invalidate();
	}
	
	public RoomVoBuilder(RoomInfo info) {
		super(info.getType());
		this.setName(info.getName()).setPeopleNum(info.getPeopleNum()).setRoomNum(info.getRoomNum()).setPrice(info.getPrice());
	}
	
	public RoomVoBuilder(String type){
		super(type);
	}

	@Override
	public RoomVoBuilder setName(String name) {
		if (checkName(name))
			super.setName(name);
		return this;
	}
	
	public RoomVoBuilder setPeopleNum(int peopleNum){
		super.setPeopleNum(peopleNum);
		return this;
	}
	
	public RoomVoBuilder setRoomNum(int roomNum){
		super.setRoomNum(roomNum);
		return this;
	}
	
	public RoomVoBuilder setPrice(double price){
		super.setPrice(price);
		return this;
	}

	@Override
	public RoomVo getRoomInfo() {
		if (!isReady())
			return null;

		return new RoomVo(Type.OTHER).setName(name).setPeopleNum(numOfPeople).setRoomNum(numOfRoom).setPrice(price);

	}

}
