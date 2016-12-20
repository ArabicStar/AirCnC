package utils.info.hotel;

import po.hotel.HotelPo;
import utils.info.hotel.RoomTemplate.Type;

/**
 * Abstract of builder for RoomInfo, assisting to assure immutable object.
 * <br>
 * <br>
 * <b>NOTICE:</b>
 * <ul>
 * <li>If choose <b>default</b> type, peopleNum will be set automatically. Else
 * peopleNum should be set before build a RoomInfo instance.<br>
 * <li>All parameters of setter will be checked firstly. If invalid, the setting
 * will not take effect, and IllgealArgumentException will be thrown. <br>
 * </ul>
 * 
 * @author jqwu
 * @see utils.info.hotel.Room
 */
public class RoomBuilder extends RoomTemplate{
	private static final Room INVALID_ROOM;
	static {
		INVALID_ROOM = new Room(Type.其它);
		INVALID_ROOM.invalidate();
	}
	
	
	/**
	 * Initialize a builder by given RoomInfo, all information will be kept.. <br>
	 * 
	 * @param type
	 */
	public RoomBuilder(Room info){
		this(info.getType());
		this.setName(info.getName()).setPeopleNum(info.getPeopleNum()).
		setRoomNum(info.getRoomNum()).setHotel(info.getHotel());
	}
	
	RoomBuilder(){}
	
	/**
	 * Initialize a builder by given type. <br>
	 * 
	 * @param type
	 */
	public RoomBuilder(String name){
		if (name == null)
			throw new IllegalArgumentException();

		this.setName(name);
	}
	
	/**
	 * Get an invalid RoomPo instance.
	 * 
	 * @return Invalid RoomPo instance
	 */
	public static final Room getInvalidInfo() {
		return INVALID_ROOM;
	}
	
	/**
	 * Set name. <br>
	 * 
	 * @param name
	 *            name string <br>
	 * @return this instance <br>
	 */
	public RoomBuilder setName(String name){
		
		this.name = name;
		try{
			type = Type.valueOf(name);
		}catch (IllegalArgumentException e){
			type = Type.其它;
		}
		
		if(type!=Type.其它){
			numOfPeople = type.ordinal()+1;
		}
//		System.out.println(type.name());
		return this;
	}
	
	
	/**
	 * Set peopleNum. <br>
	 * 
	 * @param peopleNum
	 *            peopleNum int <br>
	 * @return this instance <br>
	 */
	public RoomBuilder setPeopleNum(int peopleNum){
		if(checkPeopleNum(peopleNum)){
			this.numOfPeople = peopleNum;
		}
		return this;
	}
	
	public RoomBuilder setRoomNum(int roomNum){
		if(checkRoomNum(roomNum)){
			this.numOfRoom = roomNum;
		}
		return this;
	}
	
	public RoomBuilder setPrice(double price){
		this.price = price;
		return this;
	}
	
	public RoomBuilder setHotel(HotelPo hotel){
		this.hotel = hotel;
		return this;
	}
	
	public boolean isReady() {
		return (name != null);
	}

	public Room getRoomInfo(){
		if (isReady())
			return new Room(type).setName(name).setPeopleNum(numOfPeople).setRoomNum(numOfRoom).
					setPrice(price).setHotel(hotel);
		

		return new RoomBuilder("single").getRoomInfo();
	}

}
