package utils.info.hotel;

import po.hotel.HotelPo;

public class Room extends RoomTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = -406517527311810609L;

	protected boolean isValid;

	private static final String BLANK = "";

	public Room(String name) {
		this();
		this.name = name;
	}

	public Room() {
		name = BLANK;
		numOfPeople = 0;
		numOfRoom = 0;
		price = 0;
		id = 0;
		isValid = true;
	}

	public int getId() {
		return id;
	}
	
	public Room setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		if (isValid())
			return name;
		return null;
	}

	public Room setName(String name) {
		this.name = name;
		return this;
	}

	public int getPeopleNum() {
		if (isValid())
			return numOfPeople;
		return Integer.MIN_VALUE;
	}

	public Room setPeopleNum(int num) {
		this.numOfPeople = num;
		return this;
	}

	public int getRoomNum() {
		if (isValid())
			return numOfRoom;
		return Integer.MIN_VALUE;
	}

	public Room setRoomNum(int num) {
		this.numOfRoom = num;
		return this;
	}


	public double getPrice() {
		if (isValid())
			return price;
		return Double.MIN_VALUE;
	}

	public Room setPrice(double price) {
		this.price = price;
		return this;
	}

	public HotelPo getHotel() {
		return hotel;
	}

	public Room setHotel(HotelPo hotel) {
		this.hotel = hotel;
		return this;
	}

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}

}
