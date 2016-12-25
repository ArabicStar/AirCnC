package utils.promotion.trigger;

import java.io.Serializable;

import utils.info.order.OrderInfo;

public interface Trigger extends Serializable {
	public boolean test(OrderInfo order);

	public String when();

	public String name();
}
