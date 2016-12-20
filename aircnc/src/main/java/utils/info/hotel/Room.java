package utils.info.hotel;

import po.hotel.HotelPo;

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
	
//	Room(Room r){
//		this.id = r.getId();
//		this.name = r.getName();
//		this.numOfPeople = r.getPeopleNum();
//		this.numOfRoom = r.getRoomNum();
//		this.price = r.getPrice();
//		this.type = Type.valueOf(r.getType());
//	}
	
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
	
	public HotelPo getHotel(){
		if(isValid())
			return hotel;
		return null;
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
	
	Room setHotel(HotelPo hotel){
		this.hotel = hotel;
		return this;
	}

}
