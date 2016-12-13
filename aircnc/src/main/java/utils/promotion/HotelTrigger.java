package utils.promotion;

public abstract class HotelTrigger extends Trigger {

	public enum When {
		BIRTHDAY, MULTI, BUSINESS, PEROID
	}

	public HotelTrigger(When when) {
		super();
	}

}
