package utils.info.hotel;

public abstract class RoomInfo extends RoomInfoTemplate {
	protected boolean isValid;

	private static final String BLANK = "";

	protected RoomInfo(Type type) {
		this.type = type;
		name = BLANK;
		numOfPeople = 0;
		numOfRoom = 0;
		isValid = true;
	}

	public String getType() {
		return type.name().toLowerCase();
	}

	public abstract String getName();

	public int getPeopleNum() {
		if (isValid())
			return (type == Type.OTHER ? numOfPeople : (type.ordinal() + 1));
		return Integer.MIN_VALUE;
	}

	public int getRoomNum() {
		if (isValid())
			return numOfRoom;
		return Integer.MIN_VALUE;
	}

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}

}
