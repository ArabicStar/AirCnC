package utils.info.hotel;

import java.io.Serializable;

import po.hotel.HotelPo;

/**
 * Abstract of room info object.<br>
 * Define fields contained in RoomInfo, and checker for them.<br>
 * 
 * @author jqwu
 * 
 */
public abstract class RoomTemplate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 81449451930312412L;

	/**
	 * id int <br>
	 */
	protected int id;

	/**
	 * name string <br>
	 */
	protected String name;

	/**
	 * numOfPeople int <br>
	 */
	protected int numOfPeople;

	/**
	 * numOfRoom int <br>
	 */
	protected int numOfRoom;

	/**
	 * price double <br>
	 */
	protected double price;

	protected HotelPo hotel;

	/**
	 * check whether name is formal
	 * 
	 * @param name
	 * @return <b>false</b> if invalid <br>
	 *         <b>true</b> if valid <br>
	 */
	public static boolean checkName(String name) {
		return name != null&&name.length()>0;
	}

	/**
	 * check whether the amount of people is valid
	 * 
	 * @param peopleNum
	 * @return <b>false</b> if invalid <br>
	 *         <b>true</b> if valid <br>
	 */
	public static boolean checkPeopleNum(int peopleNum) {
		return peopleNum >= 1 && peopleNum <= 20;
	}

	/**
	 * check whether the number of room is reasonable
	 * 
	 * @param roomNum
	 * @return <b>false</b> if invalid <br>
	 *         <b>true</b> if valid <br>
	 */
	public static boolean checkRoomNum(int roomNum) {
		return roomNum >= 0 || roomNum <= -1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomTemplate other = (RoomTemplate) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
