package po.hotel;

import org.apache.commons.lang.StringUtils;

import utils.info.hotel.RoomInfo;

public class RoomPo extends RoomInfo{
	
	protected int numId = Integer.MIN_VALUE;

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
	
	RoomPo setId(int id){
		this.id = id;
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
	
	RoomPo setPrice(double price){
		this.price = price;
		return this;
	}
	
	RoomPo setHotelId(String hotelId){
		this.hotelId = hotelId;
		this.numId = Integer.valueOf(hotelId);
		return this;
	}
	
	void setNumId(int numId){
		this.numId = numId;
	}
	
	int getNumId(){
		return numId;
	}

}
