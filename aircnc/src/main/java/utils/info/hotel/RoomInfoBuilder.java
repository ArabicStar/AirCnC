package utils.info.hotel;

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
 * @see utils.info.hotel.RoomInfo
 */
public abstract class RoomInfoBuilder extends RoomInfoTemplate{
	
	/**
	 * Initialize a builder by given RoomInfo, all information will be kept.. <br>
	 * 
	 * @param type
	 */
	protected RoomInfoBuilder(RoomInfo info){
		this(info.getType());
		this.setName(info.getName()).setPeopleNum(info.getPeopleNum()).setRoomNum(info.getRoomNum());
	}
	
	/**
	 * Initialize a builder by given type. <br>
	 * 
	 * @param type
	 */
	protected RoomInfoBuilder(String type){
		if (type == null)
			throw new IllegalArgumentException();

		this.setName(type);
	}
	
	/**
	 * Set name. <br>
	 * 
	 * @param name
	 *            name string <br>
	 * @return this instance <br>
	 */
	public RoomInfoBuilder setName(String name){
		
		this.name = name.toLowerCase();
		try{
			type = Type.valueOf(name.toUpperCase());
		}catch (IllegalArgumentException e){
			type = Type.OTHER;
		}
		
		if(type!=Type.OTHER){
			numOfPeople = type.ordinal()+1;
		}
		return this;
	}
	
	
	/**
	 * Set peopleNum. <br>
	 * 
	 * @param peopleNum
	 *            peopleNum int <br>
	 * @return this instance <br>
	 */
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
	
	protected RoomInfoBuilder setPrice(double price){
		this.price = price;
		return this;
	}
	
	public boolean isReady() {
		return (name != null);
	}

	public abstract RoomInfo getRoomInfo();

}
