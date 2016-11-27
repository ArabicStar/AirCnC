package utils.info.hotel;

/**
 * Abstract of room info object.<br>
 * Define fields contained in RoomInfo, and checker for them.<br>
 * 
 * @author jqwu
 * 
 */
public abstract class RoomInfoTemplate {
	public enum Type {
		SINGLE, DOUBLE, TRIPLE, OTHER;
	}

	/**
	 * name string <br>
	 */
	protected String name;

	/**
	 * type Type <br>
	 */
	protected Type type;

	/**
	 * numOfPeople int <br>
	 */
	protected int numOfPeople;

	/**
	 * numOfRoom int <br>
	 */
	protected int numOfRoom;

	/**
	 * id int <br>
	 */
	protected int id;

	/**
	 * price double <br>
	 */
	protected double price;

	/**
	 * check whether name is formal
	 * 
	 * @param name
	 * @return <b>false</b> if invalid <br>
	 *         <b>true</b> if valid <br>
	 */
	public static boolean checkName(String name) {
		return name != null;
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
		return roomNum >= 1 || roomNum <= -1;
	}
}
