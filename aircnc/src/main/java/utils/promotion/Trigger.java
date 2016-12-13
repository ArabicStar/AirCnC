package utils.promotion;

import utils.info.order.OrderInfo;

public abstract class Trigger {
	public abstract boolean test(OrderInfo order);

	public abstract String when();
}
