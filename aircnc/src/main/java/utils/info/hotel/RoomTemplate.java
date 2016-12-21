package utils.info.hotel;

import po.hotel.HotelPo;

/**
 * Abstract of room info object.<br>
 * Define fields contained in RoomInfo, and checker for them.<br>
 * 
 * @author jqwu
 * 
 */
public abstract class RoomTemplate {
	public enum Type {
		单人间, 双人间, 三人间, 其它;

		public static Type of(String string) {
			if (string == null)
				return null;

			switch (string) {
			case "单人间":
				return 单人间;
			case "双人间":
				return 双人间;
			case "三人间":
				return 三人间;
			default:
				return 其它;
			}
		}
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
