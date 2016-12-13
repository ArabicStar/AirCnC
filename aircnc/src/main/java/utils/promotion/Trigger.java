package utils.promotion;

import utils.info.order.OrderInfo;

public abstract class Trigger {
	public enum Where {
		WEBSITE, HOTEL
	}

	private Where where;


	public Trigger(Where where) {
		this.where = where;
	}

	public abstract boolean test(OrderInfo order);

	public abstract String when();
}
