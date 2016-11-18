package utils.info.hotel;


public abstract class RoomInfoBuilder extends RoomInfoTemplate{
	protected RoomInfoBuilder(RoomInfo info){
		this(info.getType());
		this.setName(info.getName()).setPeopleNum(info.getPeopleNum()).setRoomNum(info.getRoomNum());
	}
	
	protected RoomInfoBuilder(String type){
		if (type == null)
			throw new IllegalArgumentException();

		this.type = Type.valueOf(type.toUpperCase());
		if (this.type == null)
			throw new IllegalArgumentException();
	}
	
	public abstract RoomInfoBuilder setName(String name);
	
	protected RoomInfoBuilder setPeopleNum(int peopleNum){
		if(checkPeopleNum(peopleNum)){
			this.numOfPeople = peopleNum;
		}
		return this;
	}
	
	protected RoomInfoBuilder setRoomNum(int roomNum){
		if(checkRoomNum(roomNum)){
			this.numOfRoom = roomNum;
		}
		return this;
	}
	
	public boolean isReady() {
		return (type == Type.OTHER ? name != null : true);
	}

	public abstract RoomInfo getRoomInfo();

}
