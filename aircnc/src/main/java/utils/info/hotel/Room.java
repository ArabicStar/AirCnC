package utils.info.hotel;

import org.apache.commons.lang.StringUtils;

import utils.info.hotel.RoomTemplate.Type;

public class Room extends RoomTemplate {
	protected boolean isValid;

	private static final String BLANK = "";

	protected Room(Type type) {
		this.type = type;
		name = BLANK;
		numOfPeople = 0;
		numOfRoom = 0;
		price = 0;
		isValid = true;
	}
	
	Room(){}

	public String getType() {
		return type.name();
	}

	public String getName(){
		if (isValid())
			return name;
		return null;
	}

	public int getPeopleNum() {
		if (isValid())
			return (type == Type.其它 ? numOfPeople : (type.ordinal() + 1));
		return Integer.MIN_VALUE;
	}

	public int getRoomNum() {
		if (isValid())
			return numOfRoom;
		return Integer.MIN_VALUE;
	}
	
	public int getId(){
		if (isValid())
			return id;
		return Integer.MIN_VALUE;
	}
	
	public double getPrice(){
		if(isValid())
			return price;
		return Double.MIN_VALUE;
	}

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}
	
	Room setName(String name){
		this.name = name;
		return this;
	}
	
	Room setId(int id){
		this.id = id;
		return this;
	}
	
	Room setPeopleNum(int num){
		this.numOfPeople = num;
		return this;
	}
	
	Room setRoomNum(int num){
		this.numOfRoom = num;
		return this;
	}
	
	Room setPrice(double price){
		this.price = price;
		return this;
	}

}
