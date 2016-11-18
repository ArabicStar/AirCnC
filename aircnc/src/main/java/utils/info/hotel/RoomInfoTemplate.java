package utils.info.hotel;

public abstract class RoomInfoTemplate {
	protected enum Type {
		SINGLE, DOUBLE, TRIPLE, OTHER;
	}

	protected String name;
	protected Type type;
	protected int numOfPeople;
	protected int numOfRoom;

	public static boolean checkName(String name) {
		return name != null;
	}

	public static boolean checkPeopleNum(int peopleNum) {
		return peopleNum >= 1 && peopleNum <= 20;
	}

	public static boolean checkRoomNum(int roomNum) {
		return roomNum >= 1 || roomNum <= -1;
	}
}
