package utils.promotion;

public abstract class HotelTrigger extends Trigger {

	public enum When {

	}

	public HotelTrigger(When when) {
		super(Where.HOTEL);
	}

}
