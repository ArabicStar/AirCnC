package utils.promotion.trigger;

import utils.info.order.OrderInfo;

public interface Trigger {
	public abstract boolean test(OrderInfo order);

	public abstract String when();
}
